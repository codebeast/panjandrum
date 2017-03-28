package com.codebeast.dao;

import com.codebeast.domain.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    @Override
    List<Contact> findAll();
}

