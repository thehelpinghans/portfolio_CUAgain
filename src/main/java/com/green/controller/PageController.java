package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    //임시, 사원페이지로 이동
    @GetMapping("/member/main")
    public String member(){
        return "member/main";
    }

    //임시, 어드민페이지로 이동
    @GetMapping("/admin/main")
    public String admin(){
        return "admin/main";
    }
    
    //Won 임시, 급여관리의 등록 페이지로 이동
    @GetMapping("/admin/preview")
    public String preview(){
        return "/admin/preview";
    }
}
