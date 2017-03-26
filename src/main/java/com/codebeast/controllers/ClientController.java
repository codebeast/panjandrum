package com.codebeast.controllers;

import com.codebeast.domain.Client;
import com.codebeast.exceptions.NoDuplicatesAllowedException;
import com.codebeast.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
    public ModelAndView getView(Model model) {
        final List<Client> allClients = clientService.getAllClients();
        model.addAttribute("clients", allClients);
        return new ModelAndView(VIEW_NAME, model.asMap());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Client createClient(@RequestBody @Valid Client client) throws NoDuplicatesAllowedException {
        return clientService.create(client);
    }

}
