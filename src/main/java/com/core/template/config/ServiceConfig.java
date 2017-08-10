package com.core.template.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Service模板生成的配置
 *
 * @author csy
 * @date 2017-05-07 22:12
 */
public class ServiceConfig {

    private ContextConfig contextConfig;

    private String servicePathTemplate;
    private String serviceImplPathTemplate;

    private String packageName;

    private List<String> serviceImplImports;

    public void init() {
        ArrayList<String> imports = new ArrayList<>();
        imports.add("org.springframework.stereotype.Service");
        imports.add("com." + contextConfig.getModuleName() + ".service.I" + contextConfig.getBizEnBigName() + "Service");
    }


    public String getServicePathTemplate() {
        return servicePathTemplate;
    }

    public void setServicePathTemplate(String servicePathTemplate) {
        this.servicePathTemplate = servicePathTemplate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getServiceImplPathTemplate() {
        return serviceImplPathTemplate;
    }

    public void setServiceImplPathTemplate(String serviceImplPathTemplate) {
        this.serviceImplPathTemplate = serviceImplPathTemplate;
    }

    public List<String> getServiceImplImports() {
        return serviceImplImports;
    }

    public void setServiceImplImports(List<String> serviceImplImports) {
        this.serviceImplImports = serviceImplImports;
    }

    public ContextConfig getContextConfig() {
        return contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }
}
