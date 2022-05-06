package com.h2.db.model;

import javax.persistence.*;

@Entity
@Table(name = "TBL_EMPLOYEES")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "LOGIN", columnDefinition = "VARCHAR_IGNORECASE(50) not null")
    private String login;

    @Column(name = "PASSWORD", columnDefinition = "VARCHAR_IGNORECASE(50) not null")
    private String password;

    @Column(name = "AUTHORITY", columnDefinition = "VARCHAR_IGNORECASE(50) not null")
    private String authority="ROLE_USER";

    @Column(name = "EMAIL", length = 250)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public  String getStandartAuthority(){return  "ROLE_USER";}

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}