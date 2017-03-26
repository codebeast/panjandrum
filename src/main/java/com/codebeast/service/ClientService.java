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
public class ClientService extends CRUDService<Client> {


    private final ClientRepository clientRepository;
    private final CampaignRepository campaignRepository;

    @Autowired
    public ClientService(final ClientRepository clientRepository, final CampaignRepository campaignRepository) {
        this.clientRepository = clientRepository;
        this.campaignRepository = campaignRepository;
    }

    @Override
    public boolean alreadyExists(final Client object) throws NoDuplicatesAllowedException {
        return clientRepository.findByName(object.getName()) != null;
    }

    @Override
    protected CrudRepository<Client, Long> getRepository() {
        return clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client findOne(final long id) {
        return clientRepository.findOne(id);
    }

    public Campaign createCampaign(final long id, final Campaign campaign) {
        final Client client = clientRepository.findOne(id);
        campaign.setClient(client);
        campaign.setCampaignStatus(CampaignStatus.DRAFT);
        campaign.setCampaignType(CampaignType.VOUCHER);
        campaign.setCreatedOn(new Date());
        return campaignRepository.save(campaign);
    }

    public Campaign saveCampaign(final Campaign campaign, final long id) {
        final Campaign loaded = campaignRepository.findOne(id);
        loaded.merge(campaign);
        return campaignRepository.save(loaded);
    }
}
