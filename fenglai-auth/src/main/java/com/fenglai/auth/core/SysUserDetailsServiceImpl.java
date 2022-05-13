package com.fenglai.auth.core;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @description: 自定义根据用户名获取用户的逻辑
 * 
 * @author: TJ
 * @date:  2022-05-11
 **/
@Component("sysUserDetailsService")
public class SysUserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
