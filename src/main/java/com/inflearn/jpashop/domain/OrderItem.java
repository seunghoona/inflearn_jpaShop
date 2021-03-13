package com.inflearn.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    //OrderItem입장에서 Item과 관계가
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item ;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;    //주문 가격
    private int count;         //주문 수량

    //== 생성 메소드 ==//
    // item , orderPrice 는 item에 있기 때문에
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        //재고를 삭제 해준다.
        item.removeStock(count);
        return orderItem;
    }

    //== 비지니스 로직 ==//
    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }
}
