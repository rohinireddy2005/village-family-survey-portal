package com.example.demo.service;

import com.example.demo.entity.Family;
import com.example.demo.entity.Member;
import com.example.demo.repository.FamilyRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.util.FamilyCalculationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final FamilyRepository familyRepository;

    public MemberService(MemberRepository memberRepository,
                         FamilyRepository familyRepository) {
        this.memberRepository = memberRepository;
        this.familyRepository = familyRepository;
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
        recalculateFamily(member.getFamily().getId());
    }

    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Long familyId = member.getFamily().getId();
        memberRepository.delete(member);

        recalculateFamily(familyId);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    private void recalculateFamily(Long familyId) {
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new RuntimeException("Family not found"));

        List<Member> members = memberRepository.findByFamily_Id(familyId);

        double totalIncome = FamilyCalculationUtil.calculateTotalIncome(family, members);
        family.setTotalIncome(totalIncome);
        family.setBplApl(FamilyCalculationUtil.calculateBplApl(totalIncome));

        familyRepository.save(family);
    }
}
