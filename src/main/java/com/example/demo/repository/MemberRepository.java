package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // FIXED: Correct JPA property navigation
    List<Member> findByFamily_Id(Long familyId);

    // Used in education module
    @Query("""
        SELECT m FROM Member m
        LEFT JOIN FETCH m.family
    """)
    List<Member> findAllWithFamily();
}
