package com.codebeast.dao;

import com.codebeast.domain.Account;
import com.codebeast.domain.ContactList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactListRepository extends CrudRepository<ContactList, Long> {

    @Override
    List<ContactList> findAll();

    List<ContactList> findByAccount(Account account);

    ContactList findByAccountAndId(Account acccount, long id);
}

