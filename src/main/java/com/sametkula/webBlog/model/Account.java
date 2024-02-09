package com.sametkula.webBlog.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Accounts")
@ToString
public class Account implements UserDetails {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "creation_date")
    @Builder.Default
    private Date creationDate = new Date();

    @Column(name = "email")
    private String email;

    @Column(name = "isEmailEnabled")
    private boolean isEmailEnabled;

    @Column(name = "totalPost")
    @Builder.Default
    private Integer totalPost = 0;

    @Column(name = "isAccountNonExpired")
    @Builder.Default
    private boolean isAccountNonExpired = true;

    @Column(name = "isAccountNonLocked")
    @Builder.Default
    private boolean isAccountNonLocked = true;

    @Column(name = "isCredentialsNonExpired")
    @Builder.Default
    private boolean isCredentialsNonExpired = true;

    @Column(name = "isEnabled")
    @Builder.Default
    private boolean isEnabled = true;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private AccountPicture accountPicture = null;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Post> posts = null;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> comments = null;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;


    @Override
    public List<Role> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
