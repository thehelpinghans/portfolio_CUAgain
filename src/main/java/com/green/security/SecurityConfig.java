package com.green.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {



    //DB의 인증정보를 이용해서 인증처리하는 service 커스터마이징한 빈
    @Bean
    MyUserDetailsService customUserDetailsService() {
        return new MyUserDetailsService();
    }

    //패스워드 인코더 빈 등록(@Autowired로 가져올 수 있음)
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/**").permitAll()
                        .antMatchers("/comm/checkRole").permitAll() //롤 별로 움직
                        .antMatchers("/css/**", "/js/**","/images/**").permitAll()
                        .antMatchers("/", "/comm/**").permitAll()
                        .antMatchers("/member/**","/logout").hasRole("USER")
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form->form
                        .loginPage("/")             //[GET]
                        .loginProcessingUrl("/signin")      //[POST] form태그의 action
                        .successForwardUrl("/comm/checkRole")
                        .usernameParameter("email")         //username -> email
                        .passwordParameter("pass")          //password -> pass
                        .permitAll()

                )
                .csrf(csrf->csrf.disable())
        ;
        return http.build();
    }
}
