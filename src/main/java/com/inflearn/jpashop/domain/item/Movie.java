package com.inflearn.jpashop.domain.item;

import com.inflearn.jpashop.domain.Item;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String name;
}
