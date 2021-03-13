package com.inflearn.jpashop.controller;

import com.inflearn.jpashop.domain.Item;
import com.inflearn.jpashop.domain.item.Book;
import com.inflearn.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"form"})
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@ModelAttribute BookForm itemForm, SessionStatus sessionStatus) {
        Item book = Book.builder().name(itemForm.getName())
                .price(itemForm.getPrice())
                .stockQuantity(itemForm.getStockQuantity())
                .author(itemForm.getAuthor())
                .isbn(itemForm.getIsbn()).build();

        itemService.saveItem(book);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId,
                                 Model model) {
        Item one = itemService.findOne(itemId);
        model.addAttribute("form", one);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItemForm(
            @PathVariable Long itemId,
            @ModelAttribute BookForm bookForm, SessionStatus sessionStatus) {


        itemService.updateItem(new UpdateItemDTO(bookForm));
        sessionStatus.setComplete();
        return "redirect:/items/{itemId}/edit";
    }
}
