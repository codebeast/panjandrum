package com.codebeast.dao;

import com.codebeast.domain.Campaign;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CampaignRepository extends CrudRepository<Campaign, Long> {

    Campaign findByName(String name);

    @Override
    List<Campaign> findAll();
}

