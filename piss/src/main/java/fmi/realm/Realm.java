package fmi.realm;


import fmi.hibernate.model.User;
import fmi.repository.UserRepository;
import fmi.util.MD5Util;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

public class Realm extends JdbcRealm{

    @Autowired
    UserRepository userRepository;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
        final UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        for(int i = 0; i<upToken.getPassword().length;i++){
            password = password + upToken.getPassword()[i];
        }

        User user = userRepository.findByUsernameAndPassword(username, new MD5Util().generateMD5(password));

        if(user != null){
            if(user.isArchived().equals(false) && user.isForValidation().equals(new Boolean(false))){
                AuthenticationInfo res = new SimpleAuthenticationInfo(upToken.getPrincipal(), upToken.getCredentials(), "Realm");
                return res;
            } else {
                return null;
            }
        }else{
            return null;
        }

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        String username = (String) getAvailablePrincipal(principals);
        Set<String> roleNames = new LinkedHashSet<String>();
        roleNames.add(userRepository.findByUsername(username).getRole().toString());
        return new SimpleAuthorizationInfo(roleNames);
    }
}
