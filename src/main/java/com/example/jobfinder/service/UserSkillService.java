package com.example.jobfinder.service;

import com.example.jobfinder.entity.UserSkill;
import com.example.jobfinder.repository.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSkillService {
    @Autowired
    private UserSkillRepository userSkillRepository;

    public UserSkill addUserSkill(UserSkill userSkill) {
        return userSkillRepository.save(userSkill);
    }

    public List<UserSkill> getSkillsByUserId(Long userId) {
        return userSkillRepository.findByUserId(userId);
    }
}