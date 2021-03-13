package com.inflearn.jpashop.controller;


import com.inflearn.jpashop.domain.Address;
import com.inflearn.jpashop.domain.Member;
import com.inflearn.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    @GetMapping("members/new")
    public String getMemberNew(Model model) {
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }


    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "members/createMemberForm";
        }
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(new Address(form.getCity(), form.getStreet(), form.getZipcode()));
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();

        return "members/memberList";
    }


}
