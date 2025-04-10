package manakin.ru.stalcraftmonitor.service;

import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.entity.PriceHistory;
import manakin.ru.stalcraftmonitor.repository.ItemRepository;
import manakin.ru.stalcraftmonitor.repository.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final PlatformTransactionManager transactionManager;
    private final ItemRepository itemRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           PlatformTransactionManager transactionManager,
                           PriceHistoryRepository priceHistoryRepository) {
        this.itemRepository = itemRepository;
        this.transactionManager = transactionManager;
        this.priceHistoryRepository = priceHistoryRepository;
    }

    /**
     * Транзакция для создания предмета в базе данных
     * На вход принимает item
     * item{id,name,category,description}
     *
     * @param item - предмет
     */
    @Override
    public Item createItem(Item item) {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            itemRepository.save(item);
            transactionManager.commit(transaction);
        } catch (DataAccessException e) {
            transactionManager.rollback(transaction);
            throw e;
        }
        return item;
    }

    /**
     * Транзакция для удаления предмета из базы данных
     * На вход принимает Id предмета
     * id - String(4)
     *
     * @param itemId - id предмета
     */
    @Override
    public void deleteItem(String itemId) {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            //Удаляем все записи в истории записи цен
            List<PriceHistory> priceHistory = priceHistoryRepository.findByItemId(itemId);
            for (PriceHistory priceHistoryItem : priceHistory) {
                priceHistoryRepository.deleteByItemId(itemId);
            }

            //Удаляем предмет
            itemRepository.deleteItemById(itemId);
            //Завершаем транзакцию
            transactionManager.commit(transaction);
        } catch (DataAccessException e) {
            //Откатываем транзакцию
            transactionManager.rollback(transaction);
            throw e;
        }
    }

    /**
     * Метод для получения предмета по его id
     * itemId - String(4)
     *
     * @param itemId - id предмета
     */
    @Override
    public Item getItem(String itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new ResourceNotFoundException("Предмет с заданным id не найден");
        } else {
            return itemRepository.getItemById(itemId);
        }

    }
}
