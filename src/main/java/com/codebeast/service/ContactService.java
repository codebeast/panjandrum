package com.codebeast.service;

import com.codebeast.dao.AccountRepository;
import com.codebeast.dao.ContactListRepository;
import com.codebeast.domain.Account;
import com.codebeast.domain.Contact;
import com.codebeast.domain.ContactList;
import com.codebeast.dto.NewContactList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private final ContactListRepository contactListRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public ContactService(ContactListRepository contactListRepository, AccountRepository accountRepository) {
        this.contactListRepository = contactListRepository;
        this.accountRepository = accountRepository;
    }

    public List<ContactList> availableLists(final String accountName) {
        final Account account = accountRepository.findByName(accountName);
        final List<ContactList> contactLists = contactListRepository.findByAccount(account);
        contactLists.forEach(contactList -> {
            contactList.setCount(contactList.getContacts().size());
            contactList.setContacts(null);
            contactList.setAccount(null);

        });
        return contactLists;
    }

    public ContactList saveContacts(final NewContactList newContactList, final String accountName) {
        final Account account = accountRepository.findByName(accountName);
        final List<Contact> contactList = new ArrayList<>();
        final ContactList contacts = ContactList.builder().account(account).name(newContactList.getName()).contacts(contactList).build();
        newContactList.getNumbers().forEach(number -> contactList.add(Contact.builder().mobileNumber(number).contactList(contacts).build()));
        return contactListRepository.save(contacts);
    }

    public ContactList getList(final String accountName, final int id) {
        final Account account = accountRepository.findByName(accountName);
        return contactListRepository.findByAccountAndId(account, id);
    }
}
