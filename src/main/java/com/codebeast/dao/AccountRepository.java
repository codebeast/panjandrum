package com.codebeast.dao;

import com.codebeast.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByName(String name);

}

