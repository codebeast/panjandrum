package com.codebeast.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private ContactType contactType;

    private String mobileNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private ContactList contactList;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Voucher> vouchers;

}
