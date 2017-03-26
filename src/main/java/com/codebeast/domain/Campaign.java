package com.codebeast.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 100)
    private String name;

    @ManyToOne
    private Client client;

    private CampaignStatus campaignStatus;

    private CampaignType campaignType;

    private Date createdOn;
    private Date startCampaign;
    private Date endCampaign;

    private String backgroundColor;
    private String textColor;
    private String primaryImage;
    private String secondaryImage;
    private String headline;
    private String body;

    public void merge(final Campaign campaign) {
        this.campaignStatus = campaign.campaignStatus;
        this.backgroundColor = campaign.backgroundColor;
        this.textColor = campaign.textColor;
        this.campaignType = campaign.campaignType;
        this.primaryImage = campaign.primaryImage;
        this.secondaryImage = campaign.secondaryImage;
        this.headline = campaign.headline;
        this.body = campaign.body;
    }

    @OneToMany(mappedBy = "campaign")
    private List<Contact> contactList;


}
