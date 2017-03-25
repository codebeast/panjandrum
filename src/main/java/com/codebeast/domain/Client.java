package com.codebeast.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private long id;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 100)
    private String name;

    private List<Campaign> campaignList;

    private List<VoucherBucket> codeBucketList;

}
