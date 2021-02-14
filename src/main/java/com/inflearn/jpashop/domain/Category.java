package com.inflearn.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item",
    joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")
    )
    private List<Item> items = new ArrayList<>();

    //계층구조
    //여러개의 카테고리들은 한개의 카테고리를 가질 수 있다.
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    //한개의 카테고리는 여러개의 자식을 가질 수 있다.
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }

}
