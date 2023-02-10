package kr.co.kmarket.controller.admin;

import kr.co.kmarket.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private IndexService service;

    @GetMapping("admin/index")
    public String index(){
        return "admin/index";
    }

}
