package com.example.demo.controller;

import com.example.demo.entity.Family;
import com.example.demo.entity.Member;
import com.example.demo.service.FamilyService;
import com.example.demo.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/family/{familyId}/members")
public class MemberController {

    private final MemberService memberService;
    private final FamilyService familyService;

    public MemberController(MemberService memberService,
                            FamilyService familyService) {
        this.memberService = memberService;
        this.familyService = familyService;
    }

    // ================= ADD MEMBER =================
    @GetMapping("/add")
    public String addMemberForm(@PathVariable("familyId") Long familyId, Model model) {

        Family family = familyService.getFamilyById(familyId);

        Member member = new Member();
        member.setFamily(family);

        model.addAttribute("family", family);   // required for form
        model.addAttribute("member", member);

        return "member-form";
    }

    // ================= SAVE MEMBER =================
    @PostMapping("/save")
    public String saveMember(@PathVariable("familyId") Long familyId,
                             @ModelAttribute Member member) {

        Family family = familyService.getFamilyById(familyId);
        member.setFamily(family);

        memberService.saveMember(member);

        return "redirect:/family/details/" + familyId;
    }

    // ================= EDIT MEMBER =================
    @GetMapping("/edit/{memberId}")
    public String editMember(@PathVariable("familyId") Long familyId,
                             @PathVariable("memberId") Long memberId,
                             Model model) {

        Family family = familyService.getFamilyById(familyId);
        Member member = memberService.getMemberById(memberId);

        member.setFamily(family); // attach family

        model.addAttribute("family", family);
        model.addAttribute("member", member);

        return "member-form";
    }

    // ================= DELETE MEMBER =================
    @GetMapping("/delete/{memberId}")
    public String deleteMember(@PathVariable("familyId") Long familyId,
                               @PathVariable("memberId") Long memberId) {

        memberService.deleteMember(memberId);

        return "redirect:/family/details/" + familyId;
    }
}
