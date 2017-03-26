package com.codebeast.service;

import com.codebeast.dao.CampaignRepository;
import com.codebeast.dao.ClientRepository;
import com.codebeast.domain.Campaign;
import com.codebeast.domain.CampaignStatus;
import com.codebeast.domain.CampaignType;
import com.codebeast.domain.Client;
import com.codebeast.exceptions.NoDuplicatesAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CampaignService extends CRUDService<Campaign> {


    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignService(final CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public boolean alreadyExists(final Campaign object) throws NoDuplicatesAllowedException {
        return campaignRepository.findByName(object.getName()) != null;
    }

    @Override
    protected CrudRepository<Campaign, Long> getRepository() {
        return campaignRepository;
    }

    public List<Campaign> getAllClients() {
        return campaignRepository.findAll();
    }

    public Campaign findOne(final long id) {
        return campaignRepository.findOne(id);
    }

}
