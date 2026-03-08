package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EducationService {

    private final MemberRepository memberRepository;

    public EducationService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // ================= EDUCATION NORMALIZATION (MASTER LOGIC) =================
    public static String normalizeEducation(String edu) {

        if (edu == null || edu.trim().isEmpty()) {
            return "Illiterate";
        }

        edu = edu.trim().toLowerCase();

        // Numeric classes
        if (edu.matches("\\d+")) {
            int cls = Integer.parseInt(edu);
            if (cls <= 5) return "Primary";
            if (cls <= 10) return "Secondary";
            if (cls <= 12) return "Intermediate";
            return "Graduate";
        }

        if (edu.contains("primary")) return "Primary";
        if (edu.contains("secondary") || edu.contains("high")) return "Secondary";
        if (edu.contains("inter")) return "Intermediate";
        if (edu.contains("under") || edu.contains("degree") || edu.contains("graduate")) return "Graduate";
        if (edu.contains("post") || edu.contains("pg")) return "Post Graduate";

        return "Illiterate";
    }

    // ================= EDUCATION DISTRIBUTION =================
    public Map<String, Long> educationLevelCount() {

        List<Member> members = memberRepository.findAllWithFamily();

        if (members == null || members.isEmpty()) {
            return Collections.emptyMap();
        }

        return members.stream()
                .map(m -> normalizeEducation(m.getEducation()))
                .collect(Collectors.groupingBy(
                        e -> e,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
    }

    // ================= SCHOOL CHILDREN =================
    public List<Member> getSchoolChildren() {

        return memberRepository.findAllWithFamily().stream()
                .filter(m -> m.getAge() != null)
                .filter(m -> m.getAge() >= 5 && m.getAge() <= 18)
                .filter(m -> Boolean.TRUE.equals(m.isSchoolGoing()))
                .collect(Collectors.toList());
    }

    // ================= DROPOUTS =================
    public List<Member> getDropouts() {

        return memberRepository.findAllWithFamily().stream()
                .filter(m -> m.getAge() != null)
                .filter(m -> m.getAge() >= 5 && m.getAge() <= 18)
                .filter(m -> Boolean.FALSE.equals(m.isSchoolGoing()))
                .collect(Collectors.toList());
    }
}
