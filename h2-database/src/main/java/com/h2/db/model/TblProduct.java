package com.h2.db.model;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PRODUCTS")
public class TblProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "DESC", length = 600)
    private String desc;

    public Long getId() {
        return Long.valueOf(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ProductEntity [id=" + id + ", Name=" + name +
                ", desc=" + desc +"]";
    }
}