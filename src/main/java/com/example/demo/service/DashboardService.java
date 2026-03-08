package com.example.demo.service;

import com.example.demo.entity.Family;
import com.example.demo.entity.Member;
import com.example.demo.repository.FamilyRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    private final FamilyRepository familyRepository;
    private final MemberRepository memberRepository;

    public DashboardService(FamilyRepository familyRepository,
                            MemberRepository memberRepository) {
        this.familyRepository = familyRepository;
        this.memberRepository = memberRepository;
    }

    public DashboardStats getDashboardStats() {

        DashboardStats stats = new DashboardStats();

        List<Family> families = familyRepository.findAll();
        stats.setTotalFamilies(families.size());

        long population = 0, male = 0, female = 0;
        long children = 0, elders = 0;
        long literate = 0, illiterate = 0;
        long bpl = 0, apl = 0;
        long schoolChildren = 0;
        long dropouts = 0;

        Map<String, Long> eduMap = new LinkedHashMap<>();

        for (Family family : families) {

            // ================= FAMILY HEAD =================
            population++;

            if ("Male".equalsIgnoreCase(family.getHeadGender())) male++;
            if ("Female".equalsIgnoreCase(family.getHeadGender())) female++;

            String headEdu = EducationService.normalizeEducation(family.getEducationLevel());
            if (!"Illiterate".equals(headEdu)) {
                literate++;
                eduMap.merge(headEdu, 1L, Long::sum);
            } else {
                illiterate++;
            }

            // ================= BPL / APL =================
            if ("BPL".equalsIgnoreCase(family.getBplApl())) bpl++;
            if ("APL".equalsIgnoreCase(family.getBplApl())) apl++;

            // ================= FAMILY MEMBERS =================
            List<Member> members = memberRepository.findByFamily_Id(family.getId());

            for (Member m : members) {

                population++;

                if ("Male".equalsIgnoreCase(m.getGender())) male++;
                if ("Female".equalsIgnoreCase(m.getGender())) female++;

                if (m.getAge() != null && m.getAge() <= 18) children++;
                if (m.getAge() != null && m.getAge() >= 60) elders++;

                String edu = EducationService.normalizeEducation(m.getEducation());
                if (!"Illiterate".equals(edu)) {
                    literate++;
                    eduMap.merge(edu, 1L, Long::sum);
                } else {
                    illiterate++;
                }

                // ================= SCHOOL LOGIC =================
                if (m.getAge() != null && m.getAge() >= 5 && m.getAge() <= 18) {
                    if (Boolean.TRUE.equals(m.isSchoolGoing())) {
                        schoolChildren++;
                    } else {
                        dropouts++;
                    }
                }
            }
        }

        // ================= SET STATS =================
        stats.setTotalPopulation(population);
        stats.setMaleCount(male);
        stats.setFemaleCount(female);
        stats.setChildrenCount(children);
        stats.setElderCount(elders);
        stats.setBplCount(bpl);
        stats.setAplCount(apl);
        stats.setLiterateCount(literate);
        stats.setIlliterateCount(illiterate);
        stats.setEducationLevelCounts(eduMap);
        stats.setSchoolGoingChildren(schoolChildren);
        stats.setDropouts(dropouts);

        // ================= HIGHEST EDUCATION =================
        List<String> ORDER = List.of(
                "Illiterate",
                "Primary",
                "Secondary",
                "Intermediate",
                "Graduate",
                "Post Graduate"
        );

        String highestEdu = eduMap.isEmpty() ? "N/A" :
                eduMap.keySet().stream()
                        .filter(ORDER::contains)
                        .max(Comparator.comparingInt(ORDER::indexOf))
                        .orElse("N/A");

        stats.setHighestEducation(highestEdu);

        return stats;
    }
}
