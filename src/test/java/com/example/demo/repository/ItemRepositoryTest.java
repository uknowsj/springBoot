package com.example.demo.repository;

import com.example.demo.StudyApplicationTests;
import com.example.demo.model.entity.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){

        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("삼성 노트북");

        Item newItem = itemRepository.save(item);

        Assert.assertNotNull(newItem);
    }

    @Test
    public void read(){
        Long id = 1L;
        Optional<Item> item = itemRepository.findById(id);
        Assert.assertTrue(item.isPresent());//optional이라 true false로 따짐
        item.ifPresent(i->{
            System.out.println(i);
        });
    }
}
