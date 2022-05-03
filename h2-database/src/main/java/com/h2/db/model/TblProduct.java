package com.h2.db.model;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PRODUCTS")
public class TblProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "DESC", length = 600)
    private String desc;

    @Lob
    @Column(name = "IMG")
    private String img;

    @Column(name = "PRICE")
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getId() {
        return id;
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