//package manakin.ru.stalcraftmonitor.service;
//
//import manakin.ru.stalcraftmonitor.entity.Item;
//import manakin.ru.stalcraftmonitor.repository.ItemRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class ItemServiceTest {
//
//    @Autowired
//    private ItemServiceImpl itemService;
//
//    @Test
//    void createItem() {
//
//        Item newItem = new Item();
//
//        newItem.setCategory("test");
//        newItem.setName("test_name");
//        newItem.setDescription("test_description");
//        newItem.setId("1k4g");
//
//        itemService.createItem(newItem);
//
//        Assertions.assertEquals(newItem.getCategory(), itemService.getItem("1k4g").getCategory());
//        Assertions.assertEquals(newItem.getName(), itemService.getItem("1k4g").getName());
//        Assertions.assertEquals(newItem.getDescription(), itemService.getItem("1k4g").getDescription());
//    }
//}