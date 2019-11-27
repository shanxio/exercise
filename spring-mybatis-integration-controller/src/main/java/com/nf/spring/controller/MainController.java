package com.nf.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    /**
     * *表示默认处理方式（fallback）
     * @return
     */
   @RequestMapping("/error")
    public ModelAndView not(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        modelAndView.addObject("msg","404 NOT FOUNT");
        return modelAndView;
    }

    @RequestMapping("/userLogin")
    public ModelAndView login(HttpSession session, String name){
       ModelAndView modelAndView = new ModelAndView();
       if("admin".equals(name)){
           session.setAttribute("user","true");
           modelAndView.setViewName("redirect:index");
       }else{
           modelAndView.setViewName("redirect:login");
       }

       return modelAndView;
    }
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

}
