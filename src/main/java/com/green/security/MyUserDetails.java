package com.green.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
public class MyUserDetails extends User {

    private long mno;
    private String email;
    private String name;
    private String nickName;
    private boolean social;
    private boolean deleted;

    private Map<String, Object> attributes;

    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
/*

    public MyUserDetails(MemberEntity entity){
        this(entity.getEmail(), entity.getPass(),
                entity.getRoles()
                .stream()
                .map(myRole -> new SimpleGrantedAuthority(myRole.getRole()))// "ROLE_USER" or "ROLE_ADMIN"
                .collect(Collectors.toSet()));
        this.email=entity.getEmail();
        this.name=entity.getName();
        this.nickName = entity.getNickName();    //null일 수 있으므로
        this.social=entity.isSocial();
        this.deleted= entity.isDeleted();
        this.mno=entity.getMno();
    }
*/

}
