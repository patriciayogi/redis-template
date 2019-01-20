package com.search.job.indeed.models.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class IndeedResponse {
    private IndeedSearch indeedSearch;

}
