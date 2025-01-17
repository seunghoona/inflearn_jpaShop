package com.inflearn.jpashop.repository;

import com.inflearn.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {


    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findeOne(Long orderId){
      return  em.find(Order.class,orderId);
    }

    public Order findeAllByString(OrderSearch orderSearch) {
        return em.find(Order.class, orderSearch);
    }

}
