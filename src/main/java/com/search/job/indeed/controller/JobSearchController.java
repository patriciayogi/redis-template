package com.search.job.indeed.controller;

import com.search.job.indeed.models.response.Results;
import com.search.job.indeed.models.response.SkillResults;
import com.search.job.indeed.models.response.Skill;
import com.search.job.indeed.service.IndeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@EnableCircuitBreaker
@RestController
@RequestMapping("/jobs")
public class JobSearchController {

    @Autowired
    private IndeedService indeedService;

    @GetMapping("/results/{index}") // @Valid
    public Results getResults(@PathVariable int index){
        return indeedService.searchResults(index);
    }

    @GetMapping("/results")
    public List<Results> getAllResults(){
        return indeedService.searchResults();
    }

    @DeleteMapping("/results/{index}")
    public Results deleteResults(@PathVariable int index){
        return indeedService.removeResults(index);
    }

    @DeleteMapping("/results")
    public void deleteAllResults(){
        indeedService.removeAllResults();
    }

    @PostMapping("/results")
    public void postAllResults() {
        indeedService.loadDatabase();
    }

    @CrossOrigin
    @GetMapping(value = "/skills/{index}",produces = "application/json") // @Valid
    public SkillResults getSkills(@PathVariable int index){

        Skill skillCount = Skill.builder().build();
        List<Skill> skillCountList = Collections.singletonList(skillCount);

        return SkillResults.builder().skills(skillCountList).build();
    }


    /*
    @GetMapping("/search}")
    public Results getSearch(@PathVariable int index){
        return indeedService.searchJobs(index);
    }*/

}
