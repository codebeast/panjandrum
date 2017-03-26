package com.codebeast.controllers;

import com.codebeast.domain.Campaign;
import com.codebeast.domain.Client;
import com.codebeast.exceptions.NoDuplicatesAllowedException;
import com.codebeast.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    private static final String VIEW_NAME = "clients";
    private static final String CLIENT_VIEW_NAME = "client";
    private static final String CAMPAIGN_BUILDER_VIEW_NAME = "campaign";

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

    @GetMapping("/{id}")
    public ModelAndView getClient(Model model, @PathVariable(name = "id") final long id) {
        final Client client = clientService.findOne(id);
        model.addAttribute("client", client);
        return new ModelAndView(CLIENT_VIEW_NAME, model.asMap());
    }

    @RequestMapping(value = "/{id}/createcampaign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Campaign createCampaign(@PathVariable(name = "id") final long id, @RequestBody @Valid Campaign campaign) {
        return clientService.createCampaign(id, campaign);
    }

    @GetMapping("/{id}/campaign/{campaignId}")
    public ModelAndView getCampaign(Model model, @PathVariable(name = "id") final long id, @PathVariable(name = "campaignId") final long campaignId) {
        return new ModelAndView(CAMPAIGN_BUILDER_VIEW_NAME, model.asMap());
    }

}
