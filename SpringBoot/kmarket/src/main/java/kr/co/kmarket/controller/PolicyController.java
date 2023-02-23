package kr.co.kmarket.controller;

import kr.co.kmarket.service.PolicyService;
import kr.co.kmarket.vo.TermsPolicyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PolicyController {

    @Autowired
    private PolicyService service;

    @GetMapping(value = {"temrsPolicy/", "termsPolicy/index"})
    public String index(String cate, Model model) {
        Map<String, List<TermsPolicyVO>> map = new HashMap<>();
        List<String> chapters = new ArrayList<>();

        /*** 각 장의 이름 불러오기 ***/
        List<TermsPolicyVO> chapt = service.selectPolicyGroups(cate);
        for (int i=0; i<chapt.size(); i++){
            chapters.add(chapt.get(i).getChapter());
        }
        
        /*** 각 장의 약관 내용 ***/
        for (int j=0; j<chapt.size(); j++){
            String chapter = chapters.get(j);
            map.put(chapter, service.selectPolicies(cate, chapter));
        }

        model.addAttribute("map", map);
        model.addAttribute("cate", cate);
        return "termsPolicy/index";
    }

}
