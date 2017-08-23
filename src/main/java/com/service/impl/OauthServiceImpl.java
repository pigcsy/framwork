package com.service.impl;

import com.common.entity.OauthSystem;
import com.common.entity.UserBase;
import com.common.entity.UserRoleRelation;
import com.core.base.BaseService;
import com.core.exception.GatewayException;
import com.domain.oauth.AdminUserVo;
import com.domain.oauth.OauthSystemVo;
import com.mapper.OauthSystemMapper;
import com.mapper.UserBaseMapper;
import com.mapper.UserRoleRelationMapper;
import com.service.OauthService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OauthServiceImpl extends BaseService implements OauthService {

    private static final long serialVersionUID = 1L;

    @Autowired
    UserBaseMapper userBaseMapper;

    @Autowired
    UserRoleRelationMapper userRoleRelationMapper;

    @Autowired
    OauthSystemMapper oauthSystemMapper;


    @Override
    public OauthSystemVo queryDetailsByClientId(String clientId) {
        OauthSystemVo oauthSystemVo = new OauthSystemVo();
        OauthSystem oauthSystem = oauthSystemMapper.queryDetailsByClientId(clientId);
        Assert.notNull(oauthSystem, "验证出错");
        BeanUtils.copyProperties(oauthSystem, oauthSystemVo);
        return oauthSystemVo;
    }

    @Override
    public List<String> queryRoleByUserId(Integer userId) {
        List<String> entity = new ArrayList<>();
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setUserId(userId);
        List<UserRoleRelation> list = userRoleRelationMapper.select(userRoleRelation);
        if (CollectionUtils.isNotEmpty(list)) {
            entity = list.stream().map(target -> String.valueOf(target.getRoleId())).collect(Collectors.toList());
            return entity;
        } else {
            throw new GatewayException("验证出错");
        }
    }

    @Override
    public AdminUserVo queryByUserName(String userName) {
        AdminUserVo adminUserVo = new AdminUserVo();
        UserBase userBase = userBaseMapper.queryByUserName(userName);
        Assert.notNull(adminUserVo, "验证出错");
        BeanUtils.copyProperties(userBase, adminUserVo);
        return adminUserVo;
    }

    // @Override
    // public List<MetaDataSource> queryAllResourcesByRole() {
    //     List<Object> params = Lists.newArrayList();
    //     List<MetaDataSource> metaDataSourceList = new ArrayList<>();
    //     List<OauthSystem> oauthSystemList = oauthSystemMapper.selectAll();
    //
    //
    //
    //
    //
    //
    //
    //
    //     // List<OauthSystemResponseDto> oauthSystemResponseDtoList = this.findByNative("SELECT os.system_id AS systemId,os.client_id AS clientId,os.system_type AS systemType FROM oauth_system os", params, OauthSystemResponseDto.class);
    //     // for (OauthSystemResponseDto oauthSystemResponseDto : oauthSystemResponseDtoList) {
    //     //     if (oauthSystemResponseDto != null && oauthSystemResponseDto.getSystemType() != null) {
    //     //         if (oauthSystemResponseDto.getSystemType() == 1) {
    //     //             List<Object> paramss = Lists.newArrayList();
    //     //             List<RoleResourcesResponseDto> adminRoleResourceList = this.findByNative("SELECT ar.role_id AS roleId FROM admin_role ar", paramss, RoleResourcesResponseDto.class);
    //     //             for (RoleResourcesResponseDto roleResourcesResponseDto : adminRoleResourceList) {
    //     //                 List<String> urlList = new ArrayList<>();
    //     //                 MetaDataSource metaDataSource = new MetaDataSource();
    //     //                 AdminRoleResourceEntity responseVo = new AdminRoleResourceEntity();
    //     //                 responseVo.setRoleId(roleResourcesResponseDto.getRoleId());
    //     //                 org.springframework.data.domain.Example example = org.springframework.data.domain.Example.of(responseVo);
    //     //                 List<AdminRoleResourceEntity> oauthResourceEntityList = oauthClientRepository.findAll(example);
    //     //                 for (AdminRoleResourceEntity roleResourcesResponse : oauthResourceEntityList) {
    //     //                     List<Object> param = Lists.newArrayList();
    //     //                     param.add(roleResourcesResponse.getResourceId());
    //     //                     List<OauthResourceEntity> resourceEntityList = this.findByNative("SELECT o.url AS url FROM oauth_resource o where o.resource_id=?1", param, OauthResourceEntity.class);
    //     //                     if (CollectionUtils.isNotEmpty(resourceEntityList)) {
    //     //                         for (OauthResourceEntity oauthResourceEntity : resourceEntityList) {
    //     //                             String oauthResource = oauthResourceEntity.getUrl();
    //     //                             urlList.add(oauthResource);
    //     //                             metaDataSource.setUrls(urlList);
    //     //                         }
    //     //                     }
    //     //                     metaDataSource.setRole(String.valueOf(roleResourcesResponseDto.getRoleId()));
    //     //                     metaDataSource.setSystemType(1);
    //     //                 }
    //     //                 if (CollectionUtils.isNotEmpty(metaDataSource.getUrls())) {
    //     //                     metaDataSourceList.add(metaDataSource);
    //     //                 }
    //     //             }
    //     //         } else {
    //     //             List<Object> paramss = Lists.newArrayList();
    //     //             List<SystemResourceResponseDto> systemResourceResponseDtoList = this.findByNative("SELECT osr.resource_id AS resourceId,osr.system_id AS systemId FROM oauth_system_resource osr", paramss, SystemResourceResponseDto.class);
    //     //             MetaDataSource metaDataSource = new MetaDataSource();
    //     //             List<String> urlList = new ArrayList<>();
    //     //             for (SystemResourceResponseDto systemResourceResponseDto : systemResourceResponseDtoList) {
    //     //                 List<Object> param = Lists.newArrayList();
    //     //                 param.add(systemResourceResponseDto.getResourceId());
    //     //                 List<OauthResourceEntity> oauthResourceEntityList = this.findByNative("SELECT o.url AS url FROM oauth_resource o where o.resource_id=?1", param, OauthResourceEntity.class);
    //     //                 if (CollectionUtils.isNotEmpty(oauthResourceEntityList)) {
    //     //                     for (OauthResourceEntity oauthResourceEntity : oauthResourceEntityList) {
    //     //                         String oauthResource = oauthResourceEntity.getUrl();
    //     //                         urlList.add(oauthResource);
    //     //                         metaDataSource.setUrls(urlList);
    //     //                     }
    //     //                 }
    //     //                 metaDataSource.setRole(String.valueOf(systemResourceResponseDto.getSystemId()));
    //     //                 metaDataSource.setSystemType(2);
    //     //             }
    //     //             if (CollectionUtils.isNotEmpty(metaDataSource.getUrls())) {
    //     //                 metaDataSourceList.add(metaDataSource);
    //     //             }
    //     //         }
    //     //     }
    //     //
    //     // }
    //
    //
    //
    //
    //
    //
    //     if (CollectionUtils.isNotEmpty(metaDataSourceList)) {
    //         return metaDataSourceList;
    //     } else {
    //         throw new GatewayException("未查到数据");
    //     }
    // }
}
