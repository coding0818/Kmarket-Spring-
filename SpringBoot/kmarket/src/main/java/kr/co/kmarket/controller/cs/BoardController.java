package kr.co.kmarket.controller.cs;

import kr.co.kmarket.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private CsService service;

    @GetMapping("cs/list")
    public String list(Model model, String cate1) {
        cate1 = "_"+cate1;
        model.addAttribute("cate1", cate1);
        return "cs/board/list";
    }

    @GetMapping("cs/view")
    public String view(Model model, String cate1) {
        cate1 = "_"+cate1;
        model.addAttribute("cate1", cate1);
        return "cs/board/view";
    }

    @GetMapping("cs/write")
    public String write(Model model, String cate1) {
        cate1 = "_"+cate1;
        model.addAttribute("cate1", cate1);
        return "cs/board/write";
    }

}
