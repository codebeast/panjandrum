package com.codebeast.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("campaigns")
public class CampaignController {

    private static final String VIEW_NAME = "campaigns";


    @GetMapping
    public ModelAndView getView() {
        final Map<String, String> map = new HashMap<>();
        map.put("pageName", VIEW_NAME);
        return new ModelAndView(VIEW_NAME, map);
    }
}
