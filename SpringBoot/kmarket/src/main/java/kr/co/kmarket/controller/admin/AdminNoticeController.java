package kr.co.kmarket.controller.admin;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.CsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class AdminNoticeController {

    @Autowired
    private CsService service;

    @GetMapping("admin/cs/notice/list")
    public String list (Model model, String cate2, String pg){

        String cate1 = "notice";
        List<CsVO> articles = null;
        long total = 0;

        /*** selectbox로 검색한 것이 아닌 경우 ***/
        if (cate2 == null){
            articles = service.selectCSArticlesAll(cate1, 0);
            total = service.getTotalCount(cate1, "all", null);
        /*** 검색어가 있을 경우 ***/
        } else {
            articles = service.selectCsArticles(cate1, cate2, 0);
            total = service.getTotalCount(cate1, cate2, null);
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
        return "admin/cs/notice/list";
    }

    @GetMapping("admin/cs/notice/modify")
    public String modify(Model model, int csNo, String pg) {
        CsVO art = service.selectCsArticle(csNo);

        model.addAttribute("art", art);
        model.addAttribute("pg", pg);
        return "admin/cs/notice/modify";
    }

    @PostMapping("admin/cs/notice/modify")
    public String modify(CsVO vo) {
        service.updateCsArticle(vo);
        int csNo = vo.getCsNo();
        return "redirect:/admin/cs/notice/view?csNo="+csNo;
    }

    @GetMapping("admin/cs/notice/view")
    public String view(Model model, String cate2, String pg, int csNo) {

        CsVO art = service.selectCsArticle(csNo);

        model.addAttribute("cate2", cate2);
        model.addAttribute("pg", pg);
        model.addAttribute("csNo", csNo);
        model.addAttribute("art", art);
        return "admin/cs/notice/view";}

    @GetMapping("admin/cs/notice/write")
    public String write() {return "admin/cs/notice/write";}

    @PostMapping("admin/cs/notice/write")
    public String write(CsVO vo) {
        vo.setCate1("notice");
        service.insertCsArticle(vo);
        return "redirect:/admin/cs/notice/list";
    }

}
