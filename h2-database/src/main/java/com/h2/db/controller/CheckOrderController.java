package com.h2.db.controller;

import com.h2.db.exception.RecordNotFoundException;
import com.h2.db.model.*;
import com.h2.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CheckOrderController {

    @Autowired
    CheckOrderService service;

    @Autowired
    EmployeeService service2;

    @Autowired
    OrderService service3;

    @Autowired
    ProductService service4;

    @Autowired
    CommentService CommentService;

    @Autowired
    CommentViewService CommentViewService;

    @RequestMapping("/orders")
    public String getAllProduct(Model model)
    {
        System.out.println("get All Orders");

        List<CheckorderEntity> list = service.getAllCheckOrders();

        model.addAttribute("check_order", list);

        return "list-orders";
    }

    @RequestMapping(path = "product/GamesList/{id}",method = RequestMethod.GET)
    public String AddOrder(Model model, @PathVariable("id")Optional<Long> idPro, Authentication authentication)
        throws RecordNotFoundException
    {
        EmployeeEntity id12=service2.getEmployeeByLogin(authentication.getName());
        Optional<Long> idEmpl= Optional.ofNullable(id12.getId());

        TblProduct pro=service4.getProductById(idPro.get());
        String proPrice="Price: "+pro.getPrice()+" $";

        List<CommentViewEntity> comments=CommentViewService.getCommentViewEntityByGame(pro.getName().toString());


            model.addAttribute("comments",comments);



        model.addAttribute("product",pro);
        model.addAttribute("productPrice",proPrice);
        model.addAttribute("idEmployee",idEmpl);
        model.addAttribute("idProduct",idPro);
        model.addAttribute("order", new OrderEntity());


        System.out.println("/comments " +comments.isEmpty());
        return "add-order";
    }

    @RequestMapping(path = "product/GamesList/createOrder",method = RequestMethod.POST)
    public String createOrder(OrderEntity order)
    {
        service3.createOrder(order);

        return "redirect:/product/GamesList";
    }

    @RequestMapping(value = "product/GamesList/UserOrder")
    public String UserOrder(Model model,Authentication authentication) throws RecordNotFoundException
    {
        List<CheckorderEntity> ent=service.getCheckOrderByLogin(authentication.getName());
        model.addAttribute("orderlist",ent);
        System.out.println(ent.size());

        return "UserOrder";
    }

    @RequestMapping(path = "r8")
    public String getGames(Authentication authentication) throws RecordNotFoundException {


        List<CheckorderEntity> em=service.getCheckOrderByLogin(authentication.getName());

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
            List<String> tmp=service.getCheckOrderLoginByGame(games.get(i));
            targetLogin.addAll(tmp);
        }
        //Distinct login
        targetLogin=targetLogin.stream().distinct().collect(Collectors.toList());
        //Recommend games
        List<String> targetGames=new ArrayList<>();
        //--> List recommend games
        for(int i=0;i<targetLogin.size();i++)
        {
            List<String> tmp=service.getCheckOrderGamesByLogin(targetLogin.get(i));
            targetGames.addAll(tmp);
        }
        targetGames=targetGames.stream().distinct().collect(Collectors.toList());

        List<TblProduct> products=new ArrayList<>();
        for(int i=0;i<targetGames.size();i++)
        {
            // targetLogin.add(service.getCheckOrderByGame(games.get(i)));
            List<TblProduct> tmp=service4.getProductByName(targetGames.get(i));
            products.addAll(tmp);
        }


        return products.toString();
    }


    @RequestMapping(path = "product/GamesList/AddComment/{id}",method = RequestMethod.GET)
    public String AddComment(Model model, @PathVariable("id")Optional<Long> idPro, Authentication authentication)
            throws RecordNotFoundException
    {
        EmployeeEntity id12=service2.getEmployeeByLogin(authentication.getName());
        Optional<Long> idEmpl= Optional.ofNullable(id12.getId());

        TblProduct pro=service4.getProductById(idPro.get());

        model.addAttribute("product",pro);
        model.addAttribute("idEmployee",idEmpl);
        model.addAttribute("idProduct",idPro);

        model.addAttribute("comment", new CommentEntity());

        return "add-comment";
    }

    @RequestMapping(path = "product/GamesList/createComment/{id}",method = RequestMethod.POST)
    public String createComment(CommentEntity comment,@PathVariable("id")Optional<Long> idPro)
    {
        CommentService.createComment(comment);
        String link="redirect:/product/GamesList/"+idPro.get();

        return link;
    }
}
