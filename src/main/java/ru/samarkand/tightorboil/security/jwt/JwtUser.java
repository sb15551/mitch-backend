package ru.samarkand.tightorboil.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.samarkand.tightorboil.model.Player;
import ru.samarkand.tightorboil.model.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String login;
    private final String password;
    private final String name;
    private final String surname;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Role role;

    public JwtUser(Player player) {
        this.id = player.getId();
        this.login = player.getLogin();
        this.password = player.getPassword();
        this.name = player.getName();
        this.surname = player.getSurname();
        this.enabled = player.getIsConfirm();
        this.authorities = new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(player.getRole().getCode())));
        this.role = player.getRole();
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
