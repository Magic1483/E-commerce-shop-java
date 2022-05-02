package com.h2.db.controller;


import com.h2.db.exception.RecordNotFoundException;
import com.h2.db.model.EmployeeEntity;
import com.h2.db.model.TblProduct;
import com.h2.db.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping
    public String getAllEmployees(Model model)
    {
        System.out.println("getAllProducts");

        List<TblProduct> list = service.getAllProducts();

        model.addAttribute("products", list);

        return "list-products";
    }


    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException
    {

        System.out.println("editProductById" + id);
        if (id.isPresent()) {
            TblProduct entity = service.getProductById(id.get());
            model.addAttribute("product", entity);
            return "edit-product";
        } else {
            model.addAttribute("employee", new EmployeeEntity());
            return "add-product";
        }

    }

    @RequestMapping(path = "/em2/delete/{id}")
    public String deleteProductById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException
    {

        System.out.println("deleteProductById" + id);

        service.deleteProductById(id);
        return "redirect:/product";
    }
}
