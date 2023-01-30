package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 멤버 추가 화면
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){ // 멤버 추가 동작
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = "+member.getName());

        memberService.join(member);
        return "redirect:/"; //홈 화면으로 보냄.

    }

    @GetMapping("/members")
    public String list(Model model){ // 멤버 목록 조회
        List<Member> members = memberService.findMembers(); // 서비스에서 맴버를 찾아서 리스트 변수에 저장.
        model.addAttribute("members",members); // 해당 변수를 model 객체에 넘겨줌.
        return "members/memberList";

    }

}
