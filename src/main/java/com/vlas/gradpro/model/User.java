package com.vlas.gradpro.model;


import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
//@NamedEntityGraph(name = User.GRAPH_WITH_MEALS, attributeNodes = {@NamedAttributeNode("meals")})
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends NamedEntity {

//    public static final String GRAPH_WITH_MEALS = "User.withMeals";

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    @SafeHtml
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty
    @Length(min = 5)
//    @JsonView(View.REST.class)
    @SafeHtml
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
//    @BatchSize(size = 200)
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public String getPassword() {
        return password;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                ", restaurant=" + restaurant +
                '}';
    }
}
