package kr.co.kmarket.controller.admin;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class AdminFaqController {

    @Autowired
    private CsService service;

    @GetMapping("admin/cs/faq/list")
    public String list(Model model, String cate2, String type, String pg){

        String cate1 = "faq";
        List<CsVO> articles = null;
        long total = 0;

        /*** selectbox로 검색한 것이 아닌 경우 ***/
        if (cate2 == null){
            articles = service.selectCSArticlesAll(cate1, 0);
            total = service.getTotalCount(cate1, "all", null);

        /*** 검색어가 있을 경우 ***/
        } else {
            articles = service.selectTypeArticles(cate1, cate2, type, 0);
            total = service.getTotalCount(cate1, cate2, type);
        }

        /*** 페이징 처리 ***/
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitstart(currentPage);
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        model.addAttribute("articles", articles);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("type", type);
        return "admin/cs/faq/list";
    }

    @GetMapping("admin/cs/faq/modify")
    public String modify() {
        return "admin/cs/faq/modify";
    }

    @GetMapping("admin/cs/faq/view")
    public String view() {return "admin/cs/faq/view";}

    @GetMapping("admin/cs/faq/write")
    public String write() {return "admin/cs/faq/write";}

}
