package com.codebeast.service;

import com.codebeast.dao.ClientRepository;
import com.codebeast.domain.Client;
import com.codebeast.exceptions.NoDuplicatesAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends CRUDService<Client> {


    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean alreadyExists(final Client object) throws NoDuplicatesAllowedException {
        return clientRepository.findByName(object.getName()) != null;
    }

    @Override
    protected CrudRepository<Client, Long> getRepository() {
        return clientRepository;
    }
}
