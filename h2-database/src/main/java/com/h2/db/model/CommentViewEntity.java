package com.h2.db.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "COMMENTVIEW")
public class CommentViewEntity {
    @Id
    @Column(name = "LOGIN")
    private String user;

    @Column(name = "NAME")
    private String game;

    @Column(name = "TEXT")
    private String comment_text;

    public String getUser() {
        return user;
    }

    public String getGame() {
        return game;
    }

    public String getComment_text() {
        return comment_text;
    }

    protected CommentViewEntity(){

    }
}
