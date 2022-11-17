package com.example.cookieshop.controllers;

import com.example.cookieshop.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession; //HttpSession er noget servlet noget

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(HttpSession session){
        //fra en form har man tastet username og password eller whatever og sendes hertil
        //check om session.getttribute == hvad der er hentet fra databasen
        //SELECT * from users where name ?, hvis ja så:

        //if(user == Veronica) gør:
        session.setAttribute("user", "Veronica");
        //session.setAttribute("user", new User("Veronica", "password"));
        //else redirect til loginside
        //session.setAttribute("Basket", "produkter"); //i stedet for secrets side så går man til kurven
        return "login";
    }
    //Session gemmer en tekstfil i browseren så info ikke mistes når man skifter side
    //getAttribute læser den fil i browseren
    //variablen er gemt i browseren.
    //getAttribute og setAttribute er gettere og settere til de tekstfiler
    //dependency injection, der laves nyt objekt i frameworket, altså bagved i frameworket
    //sker der HttpSession session = new HttpSession(); - sker i et interface
    //hvordan ser man det bagved? genvej Ian - Ctrl + mousepad click

    //man kan gøre det samme med model men hvordan??

    //mærkeligt eksempel på login, du ser kun secrets hvis du er logget ind
    @GetMapping("/secrets")
    public String verySecretSide(HttpSession session){
        var ses = session.getAttribute("user");
        if(ses.equals("Veronica")){
            return "secrets";
        } else return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        //udskriv noget til brugeren, at password/username var forkert
        session.invalidate();
        return "redirect:/";
    }

}
