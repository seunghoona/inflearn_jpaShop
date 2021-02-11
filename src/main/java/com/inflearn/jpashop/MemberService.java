package com.inflearn.jpashop;

import com.inflearn.jpashop.domain.MemberDTO;

public interface MemberService {

    public MemberDTO findMember(String userId) throws  Exception;

}
