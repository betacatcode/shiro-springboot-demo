package com.github.beatacatcode.config.shiro;

import com.github.beatacatcode.dao.DB;
import com.github.beatacatcode.domain.Role;
import com.github.beatacatcode.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestRealm extends AuthorizingRealm {

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("##################执行Shiro身份认证##################");
        if (StringUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user= DB.getUserByName(name);
        if(user==null){
            return null;
        }
        //封装一个带数据的对象,Shiro会拿这个对象和传进来的Token的密码进行对比,验证是否登录成功
        return new SimpleAuthenticationInfo(name, user.getPassword(), getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("##################执行Shiro权限认证##################");
        String userName = principalCollection.getPrimaryPrincipal().toString();
        User user=DB.getUserByName(userName);
        if(user!=null){
            //存放权限信息的对象
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<String> rolesStr=new ArrayList<>();
            for(Role role:user.getRoles()){
                rolesStr.add(role.getName());
            }
            info.addRoles(rolesStr);
            return info;
        }
        return null;
    }

}
