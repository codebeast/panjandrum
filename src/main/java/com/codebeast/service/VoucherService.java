package com.codebeast.service;

import com.codebeast.dao.AccountRepository;
import com.codebeast.dao.ContactListRepository;
import com.codebeast.dao.VoucherListRepository;
import com.codebeast.domain.*;
import com.codebeast.dto.NewContactList;
import com.codebeast.dto.NewVoucherList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherService {

    private final VoucherListRepository voucherListRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public VoucherService(VoucherListRepository voucherListRepository, AccountRepository accountRepository) {
        this.voucherListRepository = voucherListRepository;
        this.accountRepository = accountRepository;
    }

    public List<VoucherList> availableLists(final String accountName) {
        final Account account = accountRepository.findByName(accountName);
        final List<VoucherList> contactLists = voucherListRepository.findByAccount(account);
        contactLists.forEach(contactList -> {
            contactList.setCount(contactList.getVouchers().size());
            contactList.setAccount(null);
        });
        return contactLists;
    }

    public VoucherList saveVouchers(final NewVoucherList newVoucherList, final String accountName) {
        final Account account = accountRepository.findByName(accountName);
        final List<Voucher> vouchers = new ArrayList<>();
        final VoucherList voucherList = VoucherList.builder().account(account).name(newVoucherList.getName()).vouchers(vouchers).build();
        newVoucherList.getVouchers().forEach(code -> vouchers.add(Voucher.builder().code(code).voucherList(voucherList).build()));
        return voucherListRepository.save(voucherList);
    }

    public VoucherList getList(final String accountName, final int id) {
        final Account account = accountRepository.findByName(accountName);
        return voucherListRepository.findByAccountAndId(account, id);
    }
}
