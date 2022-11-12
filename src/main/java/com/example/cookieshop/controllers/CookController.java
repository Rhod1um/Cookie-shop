package com.example.cookieshop.controllers;

import com.example.cookieshop.models.Cookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
@Controller
public class CookController {
    //Den her controller har vi selv lavet

        @GetMapping("/session")
        public String sessionExample(HttpSession session){
            Cookie cookieObject = new Cookie(1,"Magic",150);
            session.setAttribute("cookie", cookieObject);
            return "index";
        }
    //HttpSession, det er lidt som model, det er en beholder jeg putter noget i
    //bruger setAttribute, ligesom model's addAttribute

    //lav inspect -> network på url/session endpoint og se hvad den tager ind

    //sessions kan tilgås i andre controller metoder

    //Model giver noget til thymeleaf men hvis man skifter browservindue så glemmes det

    //med sessions kan man gemme ting i indkøbskurven og skifte side til nye produkter uden
    //tingen mistes i kurven

    //session kan fx hedde basket

    @GetMapping("/getDataFromSession")
    public String getDataFromSession(HttpSession session){

        Cookie magicCookie = (Cookie) session.getAttribute("cookie");
        //^tager den cookie der blev lagt ind i ovenover metode.
        //Hvorfor typecaste cookie (Cookie) - måske fordi java har sin egen Cookie som vi overskriver

        if(magicCookie.getName().equals("Magic")){  //cookie navnet *er* Magic fra oven over
            System.out.println("you are logged in");
        } else {
            System.out.println("not logged in");
            session.invalidate();  //dræber sessionen
            return "redirect://loggedout";  //kunne gøres
        }
        //bruge sessions til login^
        //session dræbes når man lukker fanebladet

        System.out.println(magicCookie);
        return "index";
    }
}
