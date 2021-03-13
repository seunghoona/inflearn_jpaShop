package com.inflearn.jpashop.service;

import com.inflearn.jpashop.domain.Member;
import com.inflearn.jpashop.repository.MemberRepository;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {


    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    /*@Rollback(value = false)*/
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("Na");

        //when
        Long saveId = memberService.join(member);


        //then
        assertEquals(member, memberRepository.findMember(saveId));
    }


    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member = new Member();
        member.setName("Na");

        Member member2 = new Member();
        member2.setName("Na");

        //when
        memberService.join(member);


        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2); //예외가 발생해야한다.
        });
    }

}