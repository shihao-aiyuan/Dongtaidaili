package com.shiro.base;

import com.shiro.entity.LoginUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
 * @author shihao
 * @create 2020-07-21 20:54
 */
public class myShiroRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo =new SimpleAuthorizationInfo();
        LoginUser loginUser = (LoginUser)principalCollection.getPrimaryPrincipal();
        Set<String> permissions = loginUser.getPermissions();

        simpleAuthorizationInfo.setStringPermissions(permissions); //权限
        simpleAuthorizationInfo.setRoles(loginUser.getRoles()); //角色
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token =(UsernamePasswordToken)authenticationToken;

        return null;
    }
}
