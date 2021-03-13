package com.inflearn.jpashop.service;

import com.inflearn.jpashop.controller.UpdateItemDTO;
import com.inflearn.jpashop.domain.Item;
import com.inflearn.jpashop.domain.item.Book;
import com.inflearn.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    //실무에서는 사용하면 위험한 merge 방식 잘 못하면 null이 들어갈 수도 있다.
    public void saveItem(Item item){
        itemRepository.save(item);
    }
    @Transactional
    public void updateItem(UpdateItemDTO updateItemDTO) {
        Book item = (Book) itemRepository.findOne(updateItemDTO.getId());
        item.updateItem(updateItemDTO);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
