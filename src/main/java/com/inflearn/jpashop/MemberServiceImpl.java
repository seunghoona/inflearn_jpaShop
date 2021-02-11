package com.inflearn.jpashop;

import com.inflearn.jpashop.domain.MemberDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberDTO  findMember(String userId) throws Exception {

        Long memberId = memberRepository.findMemberId(userId);

        Member member = memberRepository.find(memberId);
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(member,memberDTO);
        return memberDTO;
    }
}
