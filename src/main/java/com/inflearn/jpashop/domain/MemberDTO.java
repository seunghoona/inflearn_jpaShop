package com.inflearn.jpashop.domain;

import com.inflearn.jpashop.MemberType;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class MemberDTO {

    private Long id;

    private MemberType memberType;

    private String username;

    private String userId;

    private String userPw;

}
