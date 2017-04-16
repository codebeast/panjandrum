package com.codebeast.controllers;

import com.codebeast.domain.Account;
import com.codebeast.domain.ContactList;
import com.codebeast.dto.NewContactList;
import com.codebeast.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(final ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public @ResponseBody
    List<ContactList> getContactLists() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = ((User) auth.getPrincipal()).getUsername();
        return contactService.availableLists(username);
    }

    @PostMapping
    public @ResponseBody
    ContactList createContacts(@RequestBody NewContactList newContactList) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = ((org.springframework.security.core.userdetails.User)auth.getPrincipal()).getUsername();
        final ContactList contactList = contactService.saveContacts(newContactList, username);
        contactList.setAccount(null);
        return contactList;
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ContactList getContactLists(@PathVariable final int id) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = ((User) auth.getPrincipal()).getUsername();
        return contactService.getList(username, id);
    }


}
