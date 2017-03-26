package com.codebeast.dao;

import com.codebeast.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByName(String name);

    @Override
    List<Client> findAll();
}

