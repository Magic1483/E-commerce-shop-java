package com.h2.db.service;

import com.h2.db.exception.RecordNotFoundException;
import com.h2.db.model.EmployeeEntity;
import com.h2.db.model.TblProduct;
import com.h2.db.model.repository.TblProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    TblProductRepository repository;

    public List<TblProduct> getAllProducts()
    {
        System.out.println("getAllProducts");
        List<TblProduct> result = (List<TblProduct>) repository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<TblProduct>();
        }
    }

    public TblProduct getProductById(Long id) throws RecordNotFoundException
    {
        System.out.println("getEmployeeById");
        Optional<TblProduct> product = repository.findById(id);

        if(product.isPresent()) {
            return product.get();
        } else {
            throw new RecordNotFoundException("No product record exist for given id");
        }
    }

    public TblProduct createOrUpdateProduct(TblProduct entity)
    {
        System.out.println("createOrUpdateProduct");
        // Create new entry
        if(entity.getId()  == null)
        {
            entity = repository.save(entity);

            return entity;
        }
        else
        {
            // update existing entry
            Optional<TblProduct> employee = repository.findById(entity.getId());

            if(employee.isPresent())
            {
                TblProduct newEntity = employee.get();
                newEntity.setName(entity.getName());
                newEntity.setDesc(entity.getDesc());


                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }
    }

    public void deleteProductById(Long id) throws RecordNotFoundException
    {
        System.out.println("deleteProductById");

        Optional<TblProduct> product = repository.findById(id);

        if(product.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No product record exist for given id");
        }
    }
}
