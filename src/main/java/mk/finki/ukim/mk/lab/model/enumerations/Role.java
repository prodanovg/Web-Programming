package mk.finki.ukim.mk.lab.model.enumerations;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.savedrequest.Enumerator;

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
