package com.green.security;

import com.green.domain.entity.EmployeesEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
public class MyUserDetails extends User {

    private long id;
    private String email;
    private String name;



    private long depId;
    private String depName;
    private String depManagerName;
    private long depManagerId;
    private String phone;

    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MyUserDetails(EmployeesEntity entity){
        this(entity.getEmail(), entity.getPass(),
                entity.getRoles()
                        .stream()
                        .map(myRole -> new SimpleGrantedAuthority(myRole.getRole()))// "ROLE_USER" or "ROLE_ADMIN"
                        .collect(Collectors.toSet()));
        this.email=entity.getEmail();
        this.name=entity.getName();
        this.depId=entity.getDep().getId();
        this.depName=entity.getDep().getName();
        this.depManagerName=entity.getDep().getManager().getName();
        this.depManagerId=entity.getDep().getManager().getId();
        this.phone= entity.getPhone();
        this.id = entity.getId();
    }
}
