
package com.inflearn.jpashop.service;

import com.inflearn.jpashop.domain.*;
import com.inflearn.jpashop.domain.item.Book;
import com.inflearn.jpashop.exception.NotEnoughStockException;
import com.inflearn.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Rollback(value = false)
    public void 상품주문() throws Exception{
        //given
        Member member = getMember("회원1");

        Book book = getBook("시골 JPA", 100000, 10);
        final int orderCount = 2;

        //when
        Long order = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        em.flush();
        em.clear();

        Order getOrder = orderRepository.findeOne(order);
        assertEquals(OrderStatus.ORDER,getOrder.getStatus(),"상품주문시 상태는 ORDER");
        assertEquals(1,getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야한다.");
        assertEquals(100000 * orderCount , getOrder.getTotalPrice(),"주문 수량은 가격 *  수량이다");
        assertEquals(8,book.getStockQuantity(),"주문수량만큼 재고 수량이 줄여져야한다.");
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = getMember("회원1");
        Book book = getBook("시골 JPA", 100000, 10);

        final int orderCount = 11;
        //when


        //then
        assertThrows(NotEnoughStockException.class , ()->{
            Long order = orderService.order(member.getId(), book.getId(), orderCount);
        });
    }
    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = getMember("나승후");
        Book book = getBook("시골 JPA", 100000, 10);

        final int orderCount =2 ;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);


        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findeOne(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 CANCEL 이다.");
        assertEquals(10, book.getStockQuantity(), "주문이 취소된 상태는 그만큼 재고가 증가해야한다.");

    }


    private Member getMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울","강가","123412"));
        em.persist(member);
        return member;
    }

    private Book getBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

}