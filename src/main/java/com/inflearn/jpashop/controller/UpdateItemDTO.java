package com.inflearn.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

//https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1/lecture/24309?tab=curriculum&speed=1.25&q=125911
@Getter
@Setter
public class UpdateItemDTO {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

    public UpdateItemDTO(BookForm bookForm) {
        this.id = bookForm.getId();
        this.name = bookForm.getName();
        this.price = bookForm.getPrice();
        this.stockQuantity = bookForm.getStockQuantity();
        this.author = bookForm.getAuthor();
        this.isbn = bookForm.getIsbn();
    }

}
