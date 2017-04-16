package com.codebeast.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class VoucherList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private int count;

    @ManyToOne
    @JsonBackReference
    private Account account;

    @OneToMany(mappedBy = "voucherList", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Voucher> vouchers;
}
