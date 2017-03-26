package com.codebeast.service;

import com.codebeast.dao.ContactRepository;
import com.codebeast.domain.Contact;
import com.codebeast.exceptions.NoDuplicatesAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService extends CRUDService<Contact> {


    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(final ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public boolean alreadyExists(final Contact object) throws NoDuplicatesAllowedException {
        return contactRepository.findOne(object.getId()) != null;
    }

    @Override
    protected CrudRepository<Contact, Long> getRepository() {
        return contactRepository;
    }

    public List<Contact> getAllClients() {
        return contactRepository.findAll();
    }

    public Contact findOne(final long id) {
        return contactRepository.findOne(id);
    }

}
