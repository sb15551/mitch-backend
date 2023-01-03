package ru.samarkand.tightorboil.security.user;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.samarkand.tightorboil.security.jwt.JwtUser;

@UtilityClass
public final class CurrentUserProvider {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static UserProfile getUser() {
        return getUser(getAuthentication());
    }

    public static UserProfile getUser(Authentication authentication) {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        return new UserProfile(jwtUser.getId(), jwtUser.getLogin(), jwtUser.getName(), jwtUser.getSurname(), jwtUser.getRole());
    }

}

