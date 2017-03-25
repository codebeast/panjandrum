package com.codebeast.service;

import com.codebeast.domain.Client;

public class ClientService implements CRUDService<Client> {

    @Override
    public boolean alreadyExists(final Client object) {
        return false;
    }
}
