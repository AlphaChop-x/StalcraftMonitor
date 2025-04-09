package manakin.ru.stalcraftmonitor.service;

import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.entity.PriceHistory;
import manakin.ru.stalcraftmonitor.repository.ItemRepository;
import manakin.ru.stalcraftmonitor.repository.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    @Override
    public Item getItem(String itemId) {
        return itemRepository.getItemById(itemId);
    }
}
