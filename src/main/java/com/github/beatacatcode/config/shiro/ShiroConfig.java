package com.github.beatacatcode.config.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /***
     * 测试realm
     */
    @Bean
    public Realm testRealm() {
        return new TestRealm();
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(testRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/loginPage");//登录页
        shiroFilterFactoryBean.setSuccessUrl("/index");//首页
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");//错误页面，认证不通过跳转

        Map<String,String> map=new HashMap<>();
        //按照顺序,先配置的起作用
        map.put("/logout","logout");
        map.put("/doLogin","anon");
        map.put("/admin/**","authc,roles[admin]");
        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;

    }

}
