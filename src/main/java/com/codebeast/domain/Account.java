package com.codebeast.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<ContactList> contactLists;

}
