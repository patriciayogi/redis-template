package com.search.job.indeed.service;

import com.search.job.indeed.dao.SkillDAO;
import com.search.job.indeed.models.response.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {

    @Autowired
    private SkillDAO skillDAO;

    public List<Skill> findAll(){
        return skillDAO.findAll();
    }

    public void save(Skill skill){
        skillDAO.save(skill);
    }

    public void delete(long index){
        skillDAO.delete(index);
    }

    public void deleteAll(){
        skillDAO.deleteAll();
    }

}
