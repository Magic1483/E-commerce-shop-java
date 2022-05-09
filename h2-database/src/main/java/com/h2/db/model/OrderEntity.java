package com.h2.db.model;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ORDERS")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ID_EMPL", nullable = false)
    private Long idEmpl;

    @Column(name = "ID_PRODUCT", nullable = false)
    private Long idProduct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIdEmpl() {
        return idEmpl;
    }

    public void setIdEmpl(Long idEmpl) {
        this.idEmpl = idEmpl;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

}