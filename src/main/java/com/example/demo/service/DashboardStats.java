package com.example.demo.service;

import java.util.Map;

public class DashboardStats {

    private long totalFamilies;
    private long totalPopulation;
    private long maleCount;
    private long femaleCount;
    private long childrenCount;
    private long elderCount;

    private long bplCount;
    private long aplCount;

    private long literateCount;
    private long illiterateCount;

    private Map<String, Long> educationLevelCounts;

    // ===== NEW FIELDS FOR EDUCATION SUMMARY =====
    private long schoolGoingChildren; // 5-18 years attending school
    private long dropouts;             // 5-18 years not attending school
    private String highestEducation;   // highest education in village

    // ===== GETTERS & SETTERS =====
    public long getTotalFamilies() { return totalFamilies; }
    public void setTotalFamilies(long totalFamilies) { this.totalFamilies = totalFamilies; }

    public long getTotalPopulation() { return totalPopulation; }
    public void setTotalPopulation(long totalPopulation) { this.totalPopulation = totalPopulation; }

    public long getMaleCount() { return maleCount; }
    public void setMaleCount(long maleCount) { this.maleCount = maleCount; }

    public long getFemaleCount() { return femaleCount; }
    public void setFemaleCount(long femaleCount) { this.femaleCount = femaleCount; }

    public long getChildrenCount() { return childrenCount; }
    public void setChildrenCount(long childrenCount) { this.childrenCount = childrenCount; }

    public long getElderCount() { return elderCount; }
    public void setElderCount(long elderCount) { this.elderCount = elderCount; }

    public long getBplCount() { return bplCount; }
    public void setBplCount(long bplCount) { this.bplCount = bplCount; }

    public long getAplCount() { return aplCount; }
    public void setAplCount(long aplCount) { this.aplCount = aplCount; }

    public long getLiterateCount() { return literateCount; }
    public void setLiterateCount(long literateCount) { this.literateCount = literateCount; }

    public long getIlliterateCount() { return illiterateCount; }
    public void setIlliterateCount(long illiterateCount) { this.illiterateCount = illiterateCount; }

    public Map<String, Long> getEducationLevelCounts() { return educationLevelCounts; }
    public void setEducationLevelCounts(Map<String, Long> educationLevelCounts) { this.educationLevelCounts = educationLevelCounts; }

    public long getSchoolGoingChildren() { return schoolGoingChildren; }
    public void setSchoolGoingChildren(long schoolGoingChildren) { this.schoolGoingChildren = schoolGoingChildren; }

    public long getDropouts() { return dropouts; }
    public void setDropouts(long dropouts) { this.dropouts = dropouts; }

    public String getHighestEducation() { return highestEducation; }
    public void setHighestEducation(String highestEducation) { this.highestEducation = highestEducation; }
}
