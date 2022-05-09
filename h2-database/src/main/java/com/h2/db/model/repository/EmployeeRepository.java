package com.h2.db.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.h2.db.model.EmployeeEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository 
			extends CrudRepository<EmployeeEntity, Long> {

	@Query("select e from EmployeeEntity e where e.login=:login")
	Optional<EmployeeEntity> findByLogin(@Param("login") String login);

}
