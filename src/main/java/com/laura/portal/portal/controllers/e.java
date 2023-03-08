package com.laura.portal.portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class e {
    @GetMapping(value = "/")
    public ModelAndView listPage(Model model) {

        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }
}
