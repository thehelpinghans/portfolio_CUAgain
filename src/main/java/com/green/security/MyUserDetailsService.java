package com.green.security;

import com.green.domain.entity.EmployeesEntity;
import com.green.domain.entity.EmployeesEntityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

@Log4j2
public class MyUserDetailsService implements UserDetailsService {

    //DB의 테이블에서 인증처리하기 위한 메서드
    @Autowired
    private EmployeesEntityRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //log.info(">>>>>login page: email -> username ::::: "+username);
        EmployeesEntity entity =  repo.findByEmailAndEnd(username, false)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일"));   //

        //email, pass, roles(Collection<? extends org.springframework.security.core.GrantedAuthority> authorities)
        // enum MyRole -> SimpleGrantedAuthority


        return new MyUserDetails(entity);

    }
}
