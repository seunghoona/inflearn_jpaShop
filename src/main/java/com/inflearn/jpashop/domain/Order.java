package com.inflearn.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {


    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    //OrderI
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문시간
    
    //주문상태 [ORDER ,CANCEL]
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    //연관관계 편의 메서드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //== 생성 메소드 ==//
    //연관관계되어 있는 생성메소드를 만들어서 처리하면 좋은점은 생성하는 시점은 이것만 바꾸면 되기 때문에 좋다.
    public static Order createOrder(Member member, Delivery delivery, OrderItem ...orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //== 비지니스 로직 ==/
    //취소는 이미 배송이 완료된 상태라면 취소할 수 없다.
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMPLETE){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        //orderItem에 취소를 해줘야한다. 한번주문 할 때 고객이 상품 2개 주문 할 수도 있다.
        for (OrderItem orderItem : orderItems) {
            //한번의 주문에 여러개의 상품을 주문할 수 있기 때문에
            orderItem.cancel();
        }
    }

    // == 조회 로직 == //
    
    /*
    * 전체 주문 가격 조회
    * */
    public int getTotalPrice(){
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }
}
