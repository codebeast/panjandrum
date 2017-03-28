package com.codebeast.service;

import com.codebeast.dao.ContactRepository;
import com.codebeast.domain.Campaign;
import com.codebeast.domain.Contact;
import com.codebeast.domain.ContactType;
import com.codebeast.exceptions.NoDuplicatesAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ContactService extends CRUDService<Contact> {


    private final ContactRepository contactRepository;
    private final CampaignService campaignService;

    @Autowired
    public ContactService(final ContactRepository contactRepository, final CampaignService campaignService) {
        this.contactRepository = contactRepository;
        this.campaignService = campaignService;
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

    public Contact save(final Contact contact) {
        return contactRepository.save(contact);
    }

    public void createContacts(MultipartFile file, final long campaignId) {
        File convFile = null;
        try {
            convFile = File.createTempFile("phone", ".csv");
        } catch (IOException e) {
        }

        try (final FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
        }

        try {
            final List<Contact> contacts = CSVUtils.parsePhoneNumbers(convFile);
            final Campaign campaign = campaignService.findOne(campaignId);
            contacts.forEach(c -> {
                c.setCampaign(campaign);
                c.setContactType(ContactType.PRIMARY);
                save(c);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
