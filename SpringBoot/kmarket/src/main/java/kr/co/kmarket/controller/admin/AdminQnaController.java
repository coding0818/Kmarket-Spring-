package kr.co.kmarket.controller.admin;

import kr.co.kmarket.security.MySellerDetails;
import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class AdminQnaController {

    @Autowired
    private CsService service;

    @GetMapping("admin/cs/qna/list")
    public String list(Model model, String pg){

        /*** 페이징 처리 ***/
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitstart(currentPage);
        long total = service.getTotalCount("qna", "all");
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        List<CsVO> articles = service.selectCSArticlesAll("qna", 0);

        model.addAttribute("articles", articles);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        return "admin/cs/qna/list";
    }

}
