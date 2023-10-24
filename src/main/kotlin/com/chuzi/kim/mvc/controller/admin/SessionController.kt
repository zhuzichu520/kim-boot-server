package com.chuzi.kim.mvc.controller.admin

import com.chuzi.kim.service.SessionService
import jakarta.annotation.Resource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/console/session")
class SessionController {

    @Resource
    private lateinit var sessionService: SessionService

    @GetMapping(value = ["/list"])
    fun list(model: Model): String {
        model.addAttribute("sessionList", sessionService.findAll())
        return "console/session/manage"
    }
}

