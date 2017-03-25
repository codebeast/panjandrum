package com.codebeast.controllers;

import com.codebeast.domain.Client;
import com.codebeast.exceptions.NoDuplicateException;
import com.codebeast.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("clients")
public class ClientController {

    private static final String VIEW_NAME = "clients";

    private final ClientService clientService;

    @Autowired
    public ClientController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ModelAndView getView() {
        final Map<String, String> map = new HashMap<>();
        map.put("pageName", VIEW_NAME);
        return new ModelAndView(VIEW_NAME, map);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Client createClient(@RequestBody @Valid Client client) {
        try {
            return clientService.create(client);
        } catch (NoDuplicateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
