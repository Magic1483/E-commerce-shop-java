package com.h2.db.controller;


import com.h2.db.exception.RecordNotFoundException;
import com.h2.db.model.CheckorderEntity;
import com.h2.db.model.EmployeeEntity;
import com.h2.db.model.TblProduct;
import com.h2.db.service.CheckOrderService;
import com.h2.db.service.EmployeeService;
import com.h2.db.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    EmployeeService service2;

    @Autowired
    CheckOrderService service3;

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


        List<CheckorderEntity> em=service3.getCheckOrderByLogin(authentication.getName());

        //Games current user
        List<String> games = new ArrayList<>();
        //Users, have current games
        List<String> targetLogin=new ArrayList<>();

        //get games
        for (int i=0;i<em.size();i++)
        {
            games.add(em.get(i).getName());
        }
        //get Logins
        for(int i=0;i<games.size();i++)
        {
            // targetLogin.add(service.getCheckOrderByGame(games.get(i)));
            List<String> tmp=service3.getCheckOrderLoginByGame(games.get(i));
            targetLogin.addAll(tmp);
        }
        //Distinct login
        targetLogin=targetLogin.stream().distinct().collect(Collectors.toList());
        //Recommend games
        List<String> targetGames=new ArrayList<>();
        //--> List recommend games
        for(int i=0;i<targetLogin.size();i++)
        {
            List<String> tmp=service3.getCheckOrderGamesByLogin(targetLogin.get(i));
            targetGames.addAll(tmp);
        }
        targetGames=targetGames.stream().distinct().collect(Collectors.toList());

        List<TblProduct> products=new ArrayList<>();
        for(int i=0;i<targetGames.size();i++)
        {
            // targetLogin.add(service.getCheckOrderByGame(games.get(i)));
            List<TblProduct> tmp=service.getProductByName(targetGames.get(i));
            products.addAll(tmp);
        }

        model.addAttribute("RecommendProducts", products);

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
