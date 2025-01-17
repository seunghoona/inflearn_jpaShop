package com.inflearn.jpashop.repository;


import com.inflearn.jpashop.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;


    public void save(Item item){
        em.persist(item);
    }

    public  Item findOne(Long id){
        return em.find(Item.class, id);
    }


    public List<Item> findAll(){
        return em.createQuery("select i FROM Item i ", Item.class).getResultList();
    }




}
