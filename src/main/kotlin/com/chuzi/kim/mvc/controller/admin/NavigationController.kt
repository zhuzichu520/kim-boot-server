package com.chuzi.kim.mvc.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView


@Controller
class NavigationController {
    @GetMapping(value = ["/"])
    fun index(model: ModelAndView): ModelAndView {
        model.viewName = "console/index"
        return model
    }

    @GetMapping(value = ["/webclient"])
    fun webclient(model: ModelAndView): ModelAndView {
        model.viewName = "console/webclient/index"
        return model
    }
}

