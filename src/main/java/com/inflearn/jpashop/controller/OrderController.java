package com.inflearn.jpashop.controller;

import com.inflearn.jpashop.domain.Item;
import com.inflearn.jpashop.domain.Member;
import com.inflearn.jpashop.domain.Order;
import com.inflearn.jpashop.repository.OrderSearch;
import com.inflearn.jpashop.service.ItemService;
import com.inflearn.jpashop.service.MemberService;
import com.inflearn.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;


    @GetMapping("/order")
    public String createForm(Model model) {


        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();


        model.addAttribute("members", members);
        model.addAttribute("items", items);


        return "/orders/orderForm";
    }

    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }


    @GetMapping("orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch , Model model) {
        Order orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);

        return "orders/orderList";
    }

    @PostMapping("orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId, Model model) {
        orderService.cancelOrder(orderId);
        return "redirect:orders";

    }
}
