package com.search.job.indeed.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class IndeedSearch implements Serializable {

  private String version;
  private String query;
  private String location;
  private String paginationPayload;
  private String radius;
  private boolean dupefilter;
  private boolean highlight;
  private int totalResults;
  private int start;
  private int end;
  private int pageNumber;

  private List<Results> results;

    public IndeedSearch() {

    }
}
