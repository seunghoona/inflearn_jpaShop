package com.inflearn.jpashop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void dbConnectionTest() throws Exception {
        //given
        Member member = new Member();
        member.setName("나승후A");
        //when

        Long id = memberRepository.save(member);
        Member member1 = memberRepository.find(id);
        System.out.println("member1 = " + member1);

        //then
    }
}