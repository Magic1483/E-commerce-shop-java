package com.h2.db.controller;

import com.h2.db.exception.RecordNotFoundException;
import com.h2.db.model.CheckorderEntity;
import com.h2.db.model.EmployeeEntity;
import com.h2.db.model.OrderEntity;
import com.h2.db.model.TblProduct;
import com.h2.db.service.CheckOrderService;
import com.h2.db.service.EmployeeService;
import com.h2.db.service.OrderService;
import com.h2.db.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

        model.addAttribute("product",pro);
        model.addAttribute("productPrice",proPrice);
        model.addAttribute("idEmployee",idEmpl);
        model.addAttribute("idProduct",idPro);
        model.addAttribute("order", new OrderEntity());
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
}
