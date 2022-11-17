package com.example.cookieshop.controllers;

import com.example.cookieshop.models.Cookie;
import com.example.cookieshop.repositories.CookieRepository;
import com.example.cookieshop.service.CookieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CookieController {
    CookieRepository repo = new CookieRepository();
    CookieService service = new CookieService();

    @GetMapping("/")
    public String index(HttpSession session){
        return "index";
    }

    @GetMapping("/basket")
    public String basket(HttpSession session){
        return "basket";
    }

    @GetMapping("/shop")
    public String basket(HttpSession session, Model cookieModel){
        cookieModel.addAttribute("cookies",repo.getAllCookies());
        return "shop"; //her hentes alle cookies shoppen har på menuen
    }

    @GetMapping("/addToBasket")
    public String add(@RequestParam String id, HttpSession session){
        List<Cookie> basket = new ArrayList<>();
        var c = repo.getCookieById(Integer.parseInt(id));
        basket.add(c);
        session.setAttribute("basket", basket);
        //kunne også være model her
        //sum:
        session.setAttribute("sum", service.calcPrice(basket));
        session.setAttribute("itemsInBasket", service.itemsInBasket(basket));

        return "redirect:/shop";
    }
}
