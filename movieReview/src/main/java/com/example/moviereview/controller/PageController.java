package com.example.moviereview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movie/pages")
public class PageController {

    @GetMapping("/main2")
    public ModelAndView main(){

        return new ModelAndView("main2"); //template 하위경로
    }

}
