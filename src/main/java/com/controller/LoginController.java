// package com.controller;
//
// import com.common.controller.BaseController;
// import com.common.mapper.MenuMapper;
// import com.common.mapper.UserMapper;
// import com.core.exception.InvalidKaptchaException;
// import com.common.model.User;
// import com.common.node.MenuNode;
// import com.core.log.LogManager;
// import com.core.log.factory.LogTaskFactory;
// import com.core.shiro.ShiroKit;
// import com.core.shiro.ShiroUser;
// import com.core.util.ToolUtil;
// import com.core.web.ResponseJson;
// import com.google.code.kaptcha.Constants;
// import org.apache.shiro.authc.UsernamePasswordToken;
// import org.apache.shiro.subject.Subject;
// import org.hibernate.validator.constraints.NotBlank;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.ui.Model;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.context.request.RequestContextHolder;
// import org.springframework.web.context.request.ServletRequestAttributes;
//
// import javax.servlet.http.HttpServletRequest;
// import java.util.List;
//
// import static com.core.support.HttpKit.getIp;
//
// /**
//  * 登录控制器
//  *
//  * @author csy
//  * @Date 2017年1月10日 下午8:25:24
//  */
// @RestController
// @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
// @Validated
// public class LoginController extends BaseController {
//
//     @Autowired
//     MenuMapper menuMapper;
//
//     @Autowired
//     UserMapper userMapper;
//
//     /**
//      * 跳转到主页
//      */
//     @RequestMapping(value = "redirect", method = RequestMethod.GET)
//     public String index(Model model) {
//         //获取菜单列表
//         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//         List<Integer> roleList = ShiroKit.getUser().getRoleList();
//         if (roleList == null || roleList.size() == 0) {
//             ShiroKit.getSubject().logout();
//             model.addAttribute("tips", "该用户没有角色，无法登陆");
//             return "/login.html";
//         }
//         List<MenuNode> menus = menuMapper.getMenusByRoleIds(roleList);
//         List<MenuNode> titles = MenuNode.buildTitle(menus);
//         model.addAttribute("titles", titles);
//
//         //获取用户头像
//         Integer id = ShiroKit.getUser().getId();
//         User user = userMapper.selectByPrimaryKey(id);
//         String avatar = user.getAvatar();
//         model.addAttribute("avatar", avatar);
//
//         return "/index.html";
//     }
//
//     /**
//      * 跳转到登录页面
//      */
//     @RequestMapping(value = "/login", method = RequestMethod.GET)
//     public String login() {
//         if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
//             return REDIRECT + "/";
//         } else {
//             return "/login.html";
//         }
//     }
//
//     /**
//      * 点击登录执行的动作
//      */
//     @RequestMapping(value = "login", method = RequestMethod.POST)
//     @ResponseBody
//     @ResponseJson
//     public String loginVali(@NotBlank(message = "请输入内容") String userName, @NotBlank(message = "请输入内容") String password, HttpServletRequest request) {
//
//         //验证验证码是否正确
//         if (ToolUtil.getKaptchaOnOff()) {
//             String kaptcha = super.getPara("kaptcha").trim();
//             String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//             if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equals(code)) {
//                 throw new InvalidKaptchaException();
//             }
//         }
//
//         Subject currentUser = ShiroKit.getSubject();
//         UsernamePasswordToken token = new UsernamePasswordToken(userName, password.toCharArray());
//         token.setRememberMe(true);
//
//         currentUser.login(token);
//
//         ShiroUser shiroUser = ShiroKit.getUser();
//         super.getSession().setAttribute("shiroUser", shiroUser);
//         super.getSession().setAttribute("username", shiroUser.getAccount());
//
//         LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
//
//         ShiroKit.getSession().setAttribute("sessionFlag", true);
//
//         return REDIRECT + "/";
//     }
//
//     /**
//      * 退出登录
//      */
//     @RequestMapping(value = "/logout", method = RequestMethod.GET)
//     public String logOut() {
//         LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
//         ShiroKit.getSubject().logout();
//         return REDIRECT + "/login";
//     }
// }
