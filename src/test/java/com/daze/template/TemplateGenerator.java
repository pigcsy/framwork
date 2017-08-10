package com.template;

import com.core.template.config.ContextConfig;
import com.core.template.engine.SimpleTemplateEngine;
import com.core.template.engine.base.GatewayTemplateEngine;

import java.io.IOException;

/**
 * 测试模板引擎
 *
 * @author csy
 * @date 2017-05-09 20:27
 */
public class TemplateGenerator {

    public static void main(String[] args) throws IOException {
        ContextConfig contextConfig = new ContextConfig();
        contextConfig.setBizChName("啊哈");
        contextConfig.setBizEnName("haha");
        contextConfig.setModuleName("tk");
        contextConfig.setProjectPath("D:\\tmp\\");

        //contextConfig.setAddPageSwitch(false);
        //contextConfig.setEditPageSwitch(false);

        GatewayTemplateEngine gatewayTemplateEngine = new SimpleTemplateEngine();
        gatewayTemplateEngine.setContextConfig(contextConfig);
        gatewayTemplateEngine.start();
    }

}
