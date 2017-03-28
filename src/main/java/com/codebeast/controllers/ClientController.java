package com.codebeast.controllers;

import com.codebeast.domain.Campaign;
import com.codebeast.domain.Client;
import com.codebeast.domain.Contact;
import com.codebeast.domain.ContactType;
import com.codebeast.exceptions.NoDuplicatesAllowedException;
import com.codebeast.service.CSVUtils;
import com.codebeast.service.CampaignService;
import com.codebeast.service.ClientService;
import com.codebeast.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    private static final String VIEW_NAME = "clients";
    private static final String CLIENT_VIEW_NAME = "client";
    private static final String VOUCHER_BUILDER_VIEW_NAME = "voucher_builder";
    private static final String CAMPAIGN_VIEW_NAME = "campaign";
    private static final String CONTACT_MANAGER_VIEW_NAME = "contacts";

    private final ClientService clientService;
    private final CampaignService campaignService;
    private final ContactService contactService;

    @Autowired
    public ClientController(final ClientService clientService, final CampaignService campaignService, final ContactService contactService) {
        this.clientService = clientService;
        this.campaignService = campaignService;
        this.contactService = contactService;
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
        model.addAttribute("client", clientService.findOne(id));
        model.addAttribute("campaign", campaignService.findOne(campaignId));
        return new ModelAndView(CAMPAIGN_VIEW_NAME, model.asMap());
    }

    @GetMapping("/{id}/campaign/{campaignId}/voucherbuilder")
    public ModelAndView getVoucherBuilder(Model model, @PathVariable(name = "id") final long id, @PathVariable(name = "campaignId") final long campaignId) {
        model.addAttribute("client", clientService.findOne(id));
        model.addAttribute("campaign", campaignService.findOne(campaignId));
        return new ModelAndView(VOUCHER_BUILDER_VIEW_NAME, model.asMap());
    }

    @RequestMapping(value = "/{id}/campaign/{campaignId}/voucherbuilder/savecampaign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Campaign saveCampaign(@PathVariable(name = "id") final long id, @RequestBody @Valid Campaign campaign, @PathVariable(name = "campaignId") final long campaignId) {
        final Campaign campaign1 = clientService.saveCampaign(campaign, id);
        campaign1.setClient(null);
        return campaign1;
    }


    @GetMapping("/{id}/campaign/{campaignId}/contactmanager")
    public ModelAndView getContactManager(Model model, @PathVariable(name = "id") final long id, @PathVariable(name = "campaignId") final long campaignId) {
        model.addAttribute("client", clientService.findOne(id));
        model.addAttribute("campaign", campaignService.findOne(campaignId));
        return new ModelAndView(CONTACT_MANAGER_VIEW_NAME, model.asMap());
    }


    @PostMapping("/{id}/campaign/{campaignId}/contactmanager/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                         RedirectAttributes redirectAttributes, Model model, @PathVariable(name = "id") final long id, @PathVariable(name = "campaignId") final long campaignId) {
        contactService.createContacts(file, campaignId);
        return "redirect:/dashboard";
    }


}
