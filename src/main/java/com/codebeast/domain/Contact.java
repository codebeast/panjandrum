package com.codebeast.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private long id;

    private ContactType contactType;

    private String mobileNumber;

    private List<Contact> referrals;

}
