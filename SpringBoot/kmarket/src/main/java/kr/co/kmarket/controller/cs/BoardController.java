package kr.co.kmarket.controller.cs;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private CsService service;

    @GetMapping("cs/list")
    public String list(Model model, String cate1, String cate2, String pg) {

        List<CsVO> articles = null;
        List<CsVO> faqs = null;

        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitstart(currentPage);
        long total = service.getTotalCount(cate1, cate2);
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);


        if (cate1.equals("faq")){
            faqs = service.selectFaqArticles(cate2);
        }

        if (cate2.equals("all")) {
            articles = service.selectCSArticlesAll(cate1, start);
        } else {
            articles = service.selectCsArticles(cate1, cate2, start);
        }



        cate1 = "_"+cate1;
        model.addAttribute("articles", articles);
        model.addAttribute("faqs", faqs);
        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        return "cs/board/list";
    }

    @GetMapping("cs/view")
    public String view(Model model, String cate1, String cate2, String pg, int csNo) {

        CsVO art = service.selectCsArticle(csNo);

        cate1 = "_"+cate1;
        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("pg", pg);
        model.addAttribute("csNo", csNo);
        model.addAttribute("art", art);
        return "cs/board/view";
    }

    @GetMapping("cs/write")
    public String write(Model model, String cate1, String cate2, String pg) {
        cate1 = "_"+cate1;
        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("pg", pg);
        return "cs/board/write";
    }

    @PostMapping("cs/write")
    public String write(CsVO vo) {
        return "redirect:cs/board/list";
    }

}
