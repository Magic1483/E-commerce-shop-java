package com.h2.db.model.repository;

import com.h2.db.model.CheckorderEntity;
import com.h2.db.model.EmployeeEntity;
import com.h2.db.model.OrderEntity;
import com.h2.db.model.TblProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CheckOrderRepository  extends CrudRepository<CheckorderEntity, Long> {

    @Query("select o from CheckorderEntity o where o.login=:login")
    List<CheckorderEntity> findCheckorderEntityByLogin(@Param("login") String login );
}