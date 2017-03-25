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
public class Client {

    private long id;

    private String name;

    private List<Campaign> campaignList;

    private List<CodeBucket> codeBucketList;

//    private List<>
//




}
