package com.inflearn.jpashop.service;


import com.inflearn.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ItemUpdateTest {

    @Autowired
    EntityManager entityManager;


    @Test
    public void updateTest() throws Exception{
        //given
        Book book = entityManager.find(Book.class, 1L);
        //when
        book.setName("asdfasdf");
        //then
    }
}
