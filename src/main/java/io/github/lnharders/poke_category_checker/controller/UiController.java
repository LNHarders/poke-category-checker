package io.github.lnharders.poke_category_checker.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UiController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
