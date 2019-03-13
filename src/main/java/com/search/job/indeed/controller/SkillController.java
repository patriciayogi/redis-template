package com.search.job.indeed.controller;

import com.search.job.indeed.models.response.Skill;
import com.search.job.indeed.models.response.SkillResults;
import com.search.job.indeed.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@EnableCircuitBreaker
@RestController
@RequestMapping("api/v1/skills")
public class SkillController {

    @Autowired
    private SkillsService skillsService;

    @PostMapping(produces = "application/json") // TODO @Valid @Constraint
    public void create(
            @RequestBody Skill skill){

        skillsService.save(Skill.builder().id(skill.getId()).description(skill.getDescription()).build());
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public void update(
            @RequestBody Skill skill){

        skillsService.save(Skill.builder().id(skill.getId()).description(skill.getDescription()).build());
    }

    @DeleteMapping(value = "/{index}", produces = "application/json")
    public void delete(@PathVariable long index){

        skillsService.delete(index);
    }

    @DeleteMapping(value = "/all",produces = "application/json")
    public void delete(){

        skillsService.deleteAll();
    }

    // just to test UI
    @CrossOrigin
    @GetMapping(value = "/skills", produces = "application/json") // @Valid
    public SkillResults findAll(){
        List list = skillsService.findAll();
        return SkillResults.builder().skills(list).build();
    }


}
