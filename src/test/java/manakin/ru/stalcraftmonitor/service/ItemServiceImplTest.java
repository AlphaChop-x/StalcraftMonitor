package manakin.ru.stalcraftmonitor.service;

import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    void createItemTest() {
        Item newItem = new Item("1k4g", "Атом", "Артефакт", "Очень полезный артефакт");

        when(itemRepository.existsById("1k4g")).thenReturn(false);
        when(itemRepository.save(newItem)).thenReturn(newItem);

        Item result = itemService.createItem(newItem);

        assertNotNull(result);
        assertEquals(newItem, result);
        verify(itemRepository).existsById("1k4g");
        verify(itemRepository).save(newItem);
    }

    @Test
    void createItemTestFailure() {
        Item existingItem = new Item("1k4g", "Атом", "Артефакт", "Очень полезный артефакт");

        when(itemRepository.existsById("1k4g")).thenReturn(true);

        assertThrows(ResourceNotFoundException.class, () -> {
            itemService.createItem(existingItem);
        });

        verify(itemRepository, never()).save(any());
    }
}