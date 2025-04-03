package com.example.jobfinder.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_skills")
@IdClass(UserSkillId.class)
public class UserSkill {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    // Геттеры
    public User getUser() { return user; }
    public Skill getSkill() { return skill; }

    // Сеттеры
    public void setUser(User user) { this.user = user; }
    public void setSkill(Skill skill) { this.skill = skill; }
}

class UserSkillId implements Serializable {
    private Long user; // Соответствует user_id
    private Long skill; // Соответствует skill_id

    // Конструкторы
    public UserSkillId() {}
    public UserSkillId(Long user, Long skill) {
        this.user = user;
        this.skill = skill;
    }

    // Геттеры
    public Long getUser() { return user; }
    public Long getSkill() { return skill; }

    // Сеттеры
    public void setUser(Long user) { this.user = user; }
    public void setSkill(Long skill) { this.skill = skill; }

    // Переопределение equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSkillId that = (UserSkillId) o;
        return user.equals(that.user) && skill.equals(that.skill);
    }

    @Override
    public int hashCode() {
        return 31 * user.hashCode() + skill.hashCode();
    }
}