package com.codebeast.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String code;

    @ManyToOne
    @JsonBackReference
    private Contact contact;

    @ManyToOne
    @JsonBackReference
    private VoucherList voucherList;

}
