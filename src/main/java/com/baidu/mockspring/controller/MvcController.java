package com.baidu.mockspring.controller;

import com.baidu.mockspring.entity.MemoryDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class MvcController {
    @Resource
    private MemoryDatabase memoryDatabase;

    //  @GetMapping(value = "/first")
//    public ModelAndView first() {
//
//        return new ModelAndView() {{
//            setViewName("first_view");
//            addObject("datas", memoryDatabase.getPersons());
//        }};
//    }

    @RequestMapping(value = "/second", method = RequestMethod.GET)
    public ModelAndView second() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("first_view");
        modelAndView.addObject("datas", memoryDatabase.getPersons());
        modelAndView.addObject("lock",memoryDatabase.getLock());
        return modelAndView;
    }
    @RequestMapping(value="/enum",method = RequestMethod.POST)
    public String third(@RequestParam TestEnum testEnum, Model model){
        System.err.println(testEnum.toString());
        model.addAttribute("datas", memoryDatabase.getPersons());
        return "first_view";
    }
    public enum TestEnum{
        first1,second2
    }
}
