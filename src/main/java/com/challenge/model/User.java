package com.challenge.model;

import com.challenge.enums.Authorization;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "username field cannot be empty")
    private String username;

    @Column
    @NotEmpty(message = "password field cannot be empty")
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Authorization authority;

    /*
    @OneToMany(mappedBy = "user")
    private List<EventLog> eventLogs;
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(getAuthority()));
    }

    public String getAuthority(){
        return authority.toString();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
