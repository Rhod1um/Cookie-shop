package com.example.cookieshop.service;

import com.example.cookieshop.models.Cookie;

import java.util.List;

public class CookieService {

    public String calcPrice(List<Cookie> c){
        var sum = 0;
        for (Cookie i: c) {
            sum += i.getPrice();
        }
        return Integer.toString(sum);
    }
    public String itemsInBasket(List<Cookie> c){
        return Integer.toString(c.size());
    }
}
