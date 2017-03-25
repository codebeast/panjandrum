package com.codebeast.controllers;

import com.codebeast.domain.Client;
import com.codebeast.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("clients")
public class ClientController {

    private static final String VIEW_NAME = "clients";

    private final CRUDService crudService;

    @Autowired
    public ClientController(CRUDService crudService) {
        this.crudService = crudService;
    }

    @GetMapping
    public ModelAndView getView() {
        final Map<String, String> map = new HashMap<>();
        map.put("pageName", VIEW_NAME);
        return new ModelAndView(VIEW_NAME, map);
    }


    @PostMapping("/create")
    public Client createClient(@RequestBody @Valid Client client) {


        return client;
    }
}
