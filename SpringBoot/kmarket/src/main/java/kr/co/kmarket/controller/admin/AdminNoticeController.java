package kr.co.kmarket.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AdminNoticeController {

    @GetMapping("admin/cs/notice/list")
    public String list(){
        return "admin/cs/notice/list";
    }

}
