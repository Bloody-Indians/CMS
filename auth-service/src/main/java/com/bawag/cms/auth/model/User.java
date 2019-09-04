package com.bawag.cms.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User extends Auditable<String> {

/*
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Integer id;
*/

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastame")
    private String lastName;
    @Column(name = "email")
    private String userEmail;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;
    @Column(name = "username", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;
    @Column(name = "enabled")
    @NotNull
    private Boolean enabled;

    @Column(name = "lastPasswordResetDate")
    // @Temporal(TemporalType.TIMESTAMP)
    //@NotNull
    private Date lastPasswordResetDate;

    /* @JsonInclude(JsonInclude.Include.NON_EMPTY)
     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
     private Set<Role> roles;
 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> userAuthorities;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Complaint> complaints;

    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userEmail = user.getUserEmail();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.userAuthorities = user.getUserAuthorities();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public List<Authority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(List<Authority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public Set<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }


}