package kr.co.kmarket.controller;

import kr.co.kmarket.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private IndexService service;

    @GetMapping(value = {"/", "index"})
    public String index(Model model){
       // model.addAttribute("category", "main")
       // Map<String, Object> cate = service.selectCategory();


        return "index";
    }

}
