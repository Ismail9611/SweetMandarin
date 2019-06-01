package com.isco.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@ToString(of = {"username", "email", "password"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails{


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Column(name = "email")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide an e-mail")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 30)
    private String password;


    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @ElementCollection(targetClass = UserStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_status", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserStatus> statuses;

    public User(String username, String email, String password, Set<UserRole> roles){
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    private Set<UserRole> getRoles(){
        return roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
