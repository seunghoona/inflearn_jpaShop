package com.inflearn.jpashop.domain.item;

import com.inflearn.jpashop.domain.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;

}
