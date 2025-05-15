package manakin.ru.stalcraftmonitor.controller;

import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor/items")
public class ItemRestController {


    private final ItemServiceImpl itemService;

    @Autowired
    public ItemRestController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public ResponseEntity<?> addItem(
            @RequestBody Item item
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemService.createItem(item));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(
            @PathVariable String id
    ) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(itemService.getItem(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteItem(
            @PathVariable String id
    ) {
        itemService.deleteItem(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Item deleted");
    }
}
