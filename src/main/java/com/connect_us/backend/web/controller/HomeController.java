//package com.connect_us.backend.web.controller;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j //logger
//public class HomeController {
//    private final HttpSession httpSession;
//
//    @RequestMapping("/")
//    public String home(){
//        log.info("home controller");
//        return "home";
//    }
//
////    @RequestMapping("/v1/home")
////    public String authHome(Model model){
////        SessionUser account = (SessionUser) httpSession.getAttribute("account");
////        if(account!=null){
////            model.addAttribute("userName", account.getName());
////        }
////        return "home";
////    }
//
//    /**로그아웃(get 방식 사용)**/
//    @GetMapping("/v1/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        //인증받은 사용자가 저장되어 있음
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication!=null){
//            new SecurityContextLogoutHandler().logout(request,response,authentication);
//        }
//        return "redirect:/";
//    }
//}
