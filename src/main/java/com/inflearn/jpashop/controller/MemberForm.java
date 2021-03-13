package com.inflearn.jpashop.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {



    @NotEmpty(message = "회원은 이름이 필수 값입니다. ")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
