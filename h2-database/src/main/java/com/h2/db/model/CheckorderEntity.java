package com.h2.db.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "CHECKORDER")
public class CheckorderEntity {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "LOGIN", columnDefinition = "VARCHAR_IGNORECASE(50)")
    private String login;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "PRICE")
    private Long price;

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    protected CheckorderEntity() {
    }
}