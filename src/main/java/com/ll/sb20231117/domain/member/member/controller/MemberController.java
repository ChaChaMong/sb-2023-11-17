package com.ll.sb20231117.domain.member.member.controller;

import com.ll.sb20231117.domain.member.member.entity.Member;
import com.ll.sb20231117.domain.member.member.service.MemberService;
import com.ll.sb20231117.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Validated
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/member/login")
    String showLogin() {
        return "member/member/login";
    }

    @Data
    public static class LoginForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/member/login")
    String join(@Valid LoginForm loginForm){
        Member memeber = memberService.findByUsername(loginForm.username).get();

        if (!memeber.getPassword().equals(loginForm.password)) {
            throw new IllegalArgumentException("비번이 틀립니다");
        }

        return rq.redirect("/article/list", "로그인 완료되었습니다.");
    }

    @GetMapping("/member/join")
    String showJoin() {
        return "member/member/join";
    }

    @Data
    public static class JoinForm {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/member/join")
    String join(@Valid JoinForm joinForm){
        memberService.join(joinForm.username, joinForm.password);

        return rq.redirect("/member/login", "회원가입이 완료되었습니다.");
    }
}
