package com.inflearn.jpashop.domain.item;

import com.inflearn.jpashop.controller.UpdateItemDTO;
import com.inflearn.jpashop.domain.Category;
import com.inflearn.jpashop.domain.Item;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("B")
@Setter
@Getter
@NoArgsConstructor
public class Book extends Item {

    private String author;
    private String isbn;

    @Builder
    public Book(Long id, String name, int price, int stockQuantity, List<Category> categories, String author,String isbn) {
        super(id, name, price, stockQuantity, categories);
        this.author = author;
        this.isbn   = isbn;
    }


    public void updateItem(UpdateItemDTO updateItemDTO) {
        this.setStockQuantity(updateItemDTO.getStockQuantity());
        this.setName(updateItemDTO.getName());
        this.setAuthor(author);
    }
}
