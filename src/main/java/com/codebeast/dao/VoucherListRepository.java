package com.codebeast.dao;

import com.codebeast.domain.Account;
import com.codebeast.domain.VoucherList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoucherListRepository extends CrudRepository<VoucherList, Long> {

    @Override
    List<VoucherList> findAll();

    List<VoucherList> findByAccount(Account account);

    VoucherList findByAccountAndId(Account acccount, long id);
}

