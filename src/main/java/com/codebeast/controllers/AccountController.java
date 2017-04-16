package com.codebeast.controllers;

import com.codebeast.dao.AccountRepository;
import com.codebeast.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("account")
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        accountRepository.save(Account.builder().name("user").build());
    }

    @GetMapping
    public @ResponseBody
    Account getAccount(final Account account) {
            return accountRepository.findByName("user");
    }


    @PostMapping("/create")
    public @ResponseBody Account createAccount(final Account account) {

        return null;
    }


}
