package com.connect_us.backend.web.controller.v1.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j //logger
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
public class AdminController {

    @RequestMapping
    public String home(){
        return "v1/admin/home";
    }
}
