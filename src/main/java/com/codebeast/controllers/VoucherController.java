package com.codebeast.controllers;

import com.codebeast.domain.ContactList;
import com.codebeast.domain.VoucherList;
import com.codebeast.dto.NewContactList;
import com.codebeast.dto.NewVoucherList;
import com.codebeast.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("vouchers")
public class VoucherController {

    private final VoucherService voucherService;

    @Autowired
    public VoucherController(final VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    @ResponseBody
    public List<VoucherList> getVoucherList() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = ((User) auth.getPrincipal()).getUsername();
        return voucherService.availableLists(username);
    }

    @PostMapping
    @ResponseBody
    public VoucherList createVouchers(@RequestBody NewVoucherList newVoucherList) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = ((User)auth.getPrincipal()).getUsername();
        final VoucherList voucherList = voucherService.saveVouchers(newVoucherList, username);
        voucherList.setAccount(null);
        return voucherList;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public VoucherList getVoucherLists(@PathVariable final int id) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = ((User) auth.getPrincipal()).getUsername();
        return voucherService.getList(username, id);
    }


}
