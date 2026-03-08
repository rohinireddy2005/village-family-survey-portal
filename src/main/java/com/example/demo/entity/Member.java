package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", nullable = false)
    private Family family;

    @Column(nullable = false)
    private String memberName;

    private Integer age;
    private String gender;
    private String relation;
    private String education;
    private String occupation;
    private Double income;

    @Column(nullable = false)
    private Boolean schoolGoing = false;

    // ===== Getters & Setters =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Family getFamily() { return family; }
    public void setFamily(Family family) { this.family = family; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getRelation() { return relation; }
    public void setRelation(String relation) { this.relation = relation; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }

    public Double getIncome() { return income; }
    public void setIncome(Double income) { this.income = income; }

    // ✅ Proper Boolean getters
    public Boolean getSchoolGoing() {
        return schoolGoing;
    }

    public Boolean isSchoolGoing() {
        return schoolGoing;
    }

    public void setSchoolGoing(Boolean schoolGoing) {
        this.schoolGoing = schoolGoing;
    }
}
