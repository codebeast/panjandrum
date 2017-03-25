package com.codebeast.service;

import com.codebeast.domain.Client;
import com.codebeast.exceptions.NoDuplicateException;

public class ClientService extends CRUDService<Client> {

    @Override
    public boolean alreadyExists(final Client object) throws NoDuplicateException  {
        //check existance


        return false;
    }

    @Override
    public Client create(final Client object) throws NoDuplicateException {





        return object;
    }
}
