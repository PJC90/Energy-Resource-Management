package dashboard.energy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dashboard.energy.entities.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"authorities", "accountNonExpired", "enabled",
        "accountNonLocked", "credentialsNonExpired","company"})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID userId;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String avatar;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
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
