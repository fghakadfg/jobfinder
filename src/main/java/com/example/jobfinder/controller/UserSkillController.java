package com.example.jobfinder.controller;

import com.example.jobfinder.entity.UserSkill;
import com.example.jobfinder.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicant")
public class UserSkillController {
    @Autowired
    private UserSkillService userSkillService;

    @PostMapping("/user-skills")
    public ResponseEntity<UserSkill> addUserSkill(@RequestBody UserSkill userSkill) {
        return ResponseEntity.ok(userSkillService.addUserSkill(userSkill));
    }

    @GetMapping("/user-skills")
    public ResponseEntity<List<UserSkill>> getSkillsByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(userSkillService.getSkillsByUserId(userId));
    }
}