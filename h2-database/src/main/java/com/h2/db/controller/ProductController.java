package com.h2.db.controller;


import com.h2.db.exception.RecordNotFoundException;
import com.h2.db.model.EmployeeEntity;
import com.h2.db.model.TblProduct;
import com.h2.db.service.EmployeeService;
import com.h2.db.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    EmployeeService service2;

    @RequestMapping
    public String getAllProduct(Model model)
    {
        System.out.println("getAllProducts");

        List<TblProduct> list = service.getAllProducts();

        model.addAttribute("products", list);

        return "list-products";
    }



    @RequestMapping(value = "/GamesList",method = RequestMethod.GET)
    public String getAllProductForUsers(Model model,Authentication authentication) throws RecordNotFoundException {
        System.out.println("getAllProducts");

        List<TblProduct> list = service.getAllProducts();

        model.addAttribute("products", list);
        System.out.println(authentication.getAuthorities().toString());

        String role=authentication.getAuthorities().toString();
        model.addAttribute("role",role);

        return "GamesList";
    }


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) throws RecordNotFoundException {
        return service2.getEmployeeByLogin(authentication.getName()).toString();
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
            model.addAttribute("product", new TblProduct());
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

    @RequestMapping(path = "edit/createProduct", method = RequestMethod.POST)
    public String createOrUpdateProduct(TblProduct product)
    {
        System.out.println("createOrUpdateProduct ");

        service.createOrUpdateProduct(product);

        return "redirect:/product";
    }
}
