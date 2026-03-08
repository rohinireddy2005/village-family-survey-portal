package com.example.demo.repository;

import com.example.demo.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FamilyRepository extends JpaRepository<Family, Long> {

    List<Family> findByFamilyNameContainingIgnoreCase(String familyName);

    List<Family> findByBplAplIgnoreCase(String bplApl);

    List<Family> findByFamilyNameContainingIgnoreCaseAndBplAplIgnoreCase(
            String familyName, String bplApl);
}
