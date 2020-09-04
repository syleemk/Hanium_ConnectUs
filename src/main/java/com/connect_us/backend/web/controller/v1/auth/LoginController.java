//package com.connect_us.backend.web.controller.v1.auth;
//
//import com.connect_us.backend.service.account.impl.AccountServiceImp;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
////@RestController //result: json
//@Controller
//@Slf4j //logger
//@RequiredArgsConstructor
//@RequestMapping("/v1/auth")
//public class LoginController {
//    private final AccountServiceImp userServiceImp;
//    private final PasswordEncoder passwordEncoder;
//
//    @RequestMapping
//    public String home(){
//        log.info("login home");
//        return "v1/auth/home";
//    }
//
////    /**
////     * 회원가입
////     * */
////    @GetMapping("/users")
////    public String getUser(){
////        return "v1/auth/users";
////    }
////
////    @PostMapping("/users")
////    public String postUser(@Valid AccountDto accountDto, BindingResult result){
////        if(result.hasErrors()){
////            return "v1/auth/users";
////        }
////        userServiceImp.save(accountDto);
////        System.out.println("회원 가입 성공");
////        return "redirect:/v1/auth/login";
////    }
//
//    /**
//     * 로그인
//     * */
//    @GetMapping("/login")
//    public String getLogin(@RequestParam(value="error", required = false) String error,
//                            @RequestParam(value="exception", required = false) String exception, Model model){
//        model.addAttribute("error",error);
//        model.addAttribute("exception",exception);
//        return "v1/auth/login";
//    }
//}
