package com.cosmetics.cosmetics.Security.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cosmetics.cosmetics.Model.Entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    @JsonIgnore
    private String password;

    private  boolean status;


    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(int id, String username, String password,boolean status,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;   
        this.username = username;
        this.password = password;
        this.status = status;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Account account) {
    	
    	Collection<? extends GrantedAuthority> authorities = Collections
    			.singleton(new SimpleGrantedAuthority(account.getRole().getName()));
    	
        return new UserDetailsImpl(
        		account.getId(),        	
        		account.getUsername(),
        		account.getPassword(),
                account.isStatus(),
            authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
