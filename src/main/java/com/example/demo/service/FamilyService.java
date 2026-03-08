package com.example.demo.service;

import com.example.demo.entity.Family;
import com.example.demo.repository.FamilyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    private final FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public List<Family> getAllFamilies() {
        return familyRepository.findAll();
    }

    @Transactional
    public Family getFamilyById(Long id) {
        Family family = familyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Family not found"));

        family.getMembers().size(); // initialize lazy
        return family;
    }

    public void saveFamily(Family family) {
        familyRepository.save(family);
    }

    public void deleteFamily(Long id) {
        familyRepository.deleteById(id);
    }

    public List<Family> searchFamilies(String name, String bplApl) {
        if ((name == null || name.isEmpty()) && (bplApl == null || bplApl.isEmpty())) {
            return familyRepository.findAll();
        }
        if (name != null && !name.isEmpty() && bplApl != null && !bplApl.isEmpty()) {
            return familyRepository.findByFamilyNameContainingIgnoreCaseAndBplAplIgnoreCase(name, bplApl);
        }
        if (name != null && !name.isEmpty()) {
            return familyRepository.findByFamilyNameContainingIgnoreCase(name);
        }
        return familyRepository.findByBplAplIgnoreCase(bplApl);
    }
}
