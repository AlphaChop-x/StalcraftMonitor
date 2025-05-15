package manakin.ru.stalcraftmonitor.controller;

import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/monitor/items/zaglushka")
public class ItemController {

    private final ItemServiceImpl itemService;

    @Autowired
    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public String itemListView(Model model) {
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "itemList";
    }

    @GetMapping("/showBlank")
    public String createBlankItem() {
        return "itemPost";
    }

    @PostMapping()
    public String addItem(
            @ModelAttribute("itemId") String itemId,
            @ModelAttribute("category") String category,
            @ModelAttribute("itemName") String itemName,
            @ModelAttribute("itemDescription") String itemDescription,
            Model model
    ) {
        Item item = new Item();

        item.setCategory(category);
        item.setName(itemName);
        item.setDescription(itemDescription);
        item.setId(itemId);

        itemService.createItem(item);
        List<Item> items = itemService.getAllItems();

        model.addAttribute("items", items);

        return "itemList";
    }
}
