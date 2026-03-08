package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "family")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false)
    private String familyType;

    @Column(nullable = false)
    private String headName;

    @Column(nullable = false)
    private String headOccupation;

    private Double headIncome;

    private Double totalIncome;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String villageName;

    private String category;
    private String bplApl;
    private String headGender;
    private String educationLevel;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    // ===== Getters & Setters =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }

    public String getFamilyType() { return familyType; }
    public void setFamilyType(String familyType) { this.familyType = familyType; }

    public String getHeadName() { return headName; }
    public void setHeadName(String headName) { this.headName = headName; }

    public String getHeadOccupation() { return headOccupation; }
    public void setHeadOccupation(String headOccupation) { this.headOccupation = headOccupation; }

    public Double getHeadIncome() { return headIncome; }
    public void setHeadIncome(Double headIncome) { this.headIncome = headIncome; }

    public Double getTotalIncome() { return totalIncome; }
    public void setTotalIncome(Double totalIncome) { this.totalIncome = totalIncome; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getVillageName() { return villageName; }
    public void setVillageName(String villageName) { this.villageName = villageName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getBplApl() { return bplApl; }
    public void setBplApl(String bplApl) { this.bplApl = bplApl; }

    public String getHeadGender() { return headGender; }
    public void setHeadGender(String headGender) { this.headGender = headGender; }

    public String getEducationLevel() { return educationLevel; }
    public void setEducationLevel(String educationLevel) { this.educationLevel = educationLevel; }

    public List<Member> getMembers() { return members; }
    public void setMembers(List<Member> members) { this.members = members; }
}
