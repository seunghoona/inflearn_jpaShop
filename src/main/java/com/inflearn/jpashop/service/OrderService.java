package com.inflearn.jpashop.service;

import com.inflearn.jpashop.domain.*;
import com.inflearn.jpashop.repository.ItemRepository;
import com.inflearn.jpashop.repository.MemberRepository;
import com.inflearn.jpashop.repository.OrderRepository;
import com.inflearn.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /*
        주문
     */
    public Long order(Long memberId, Long itemId, int count) {
        
        //멤버 조회
        Member member = memberRepository.findMember(memberId);
        //상품 조회
        Item item = itemRepository.findOne(itemId);
        

        //배송정보 생성
        //회원의 주소 정보를 저장한다.
        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY);
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        //상품 , 가격, 수량
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        //order 하나만 저장해도 orderItem ,Delivery까지 한꺼번에 저장하게 된다.
        orderRepository.save(order);


        return order.getId();
    }

    //취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findeOne(orderId);
        //주문 취소
        order.cancel();
    }

    public Order findOrders(OrderSearch orderSearch) {
        return orderRepository.findeAllByString(orderSearch);
    }


    //검색
    /*public List<Order> findOrder(OrderSearch orderSearch){
        return orderRepository.findeAll(orderSearch);
    }*/
}
