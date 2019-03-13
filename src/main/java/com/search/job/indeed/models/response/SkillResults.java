package com.search.job.indeed.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillResults implements Serializable {

        private static final long serialVersionUID = 1L;


        private List<Skill> skills;


}
