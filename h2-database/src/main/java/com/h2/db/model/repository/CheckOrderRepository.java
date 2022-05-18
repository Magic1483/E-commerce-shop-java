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


    @Query("select o from CheckorderEntity o  where o.name=:game")
    List<CheckorderEntity> findCheckorderEntityByGame(@Param("game") String game );

    @Query("select distinct login from  CheckorderEntity where name =:game")
    List<String> findCheckorderLoginByGame(@Param("game") String game );

    @Query("select distinct name from  CheckorderEntity where login=:login")
    List<String> findCheckorderGamesByLogin(@Param("login") String login );
}