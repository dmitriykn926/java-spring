package com.dy.dev.controller;

import com.dy.dev.dto.UserReadDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute
    public List<String> attributes() {
        return List.of("Hello", "World");
    }

    @GetMapping
    public String greeting() {
        return "greetings/hello";
    }

    @GetMapping("/hello")
    public ModelAndView greeting2(ModelAndView modelAndView, HttpServletRequest httpServletRequest,
                                  UserReadDto user, Model model) {
//        modelAndView.addObject("user", new UserReadDto(1L, "Ivan")); // TODO. Request Scope
        modelAndView.setViewName("greetings/hello");
        model.addAttribute("user", user);
        return modelAndView;
    }

    @GetMapping("/hello/{id}")
    public ModelAndView greeting(ModelAndView modelAndView, HttpServletRequest httpServletRequest,
                                 @RequestParam("age") Integer age,
                                 @RequestHeader("accept") String accept,
                                 @CookieValue("JSESSIONID") String jSessionId,
                                 @PathVariable("id") Integer id) {
        String ageParam = httpServletRequest.getParameter("age");
        String acceptHeader = httpServletRequest.getHeader("accept");
        Cookie[] cookies = httpServletRequest.getCookies();

        return new ModelAndView("greetings/hello");
    }

    @GetMapping("/bye")
    public ModelAndView bye(@SessionAttribute("user") UserReadDto user) {
        return new ModelAndView("greetings/bye");
    }
}
