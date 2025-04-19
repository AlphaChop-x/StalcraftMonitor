package manakin.ru.stalcraftmonitor.controller.rest;

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

    @PostMapping()
    public ResponseEntity<String> addItem(
            @ModelAttribute("itemId") String itemId,
            @ModelAttribute("category") String category,
            @ModelAttribute("itemName") String itemName,
            @ModelAttribute("itemDescription") String itemDescription
    ) {
        Item item = new Item();

        item.setCategory(category);
        item.setName(itemName);
        item.setDescription(itemDescription);
        item.setId(itemId);

        itemService.createItem(item);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Item created successfully");
    }
}
