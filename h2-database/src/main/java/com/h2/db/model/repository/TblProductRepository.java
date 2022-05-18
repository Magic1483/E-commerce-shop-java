package com.h2.db.model.repository;

import com.h2.db.model.CheckorderEntity;
import com.h2.db.model.TblProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblProductRepository  extends CrudRepository<TblProduct, Long> {

    @Query("select p from TblProduct p where p.name=:name")
    List<TblProduct> findProductByName(@Param("name") String game );
}