package manakin.ru.stalcraftmonitor.service;

import manakin.ru.stalcraftmonitor.entity.Favorite;
import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.entity.PriceHistory;
import manakin.ru.stalcraftmonitor.repository.FavoriteRepository;
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
    private final ItemRepository itemRepository;
    private final PlatformTransactionManager transactionManager;
    private final PriceHistoryRepository priceHistoryRepository;
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, PriceHistoryRepository priceHistory, PlatformTransactionManager transactionManager, PriceHistoryRepository priceHistoryRepository, FavoriteRepository favoriteRepository) {
        this.itemRepository = itemRepository;
        this.transactionManager = transactionManager;
        this.priceHistoryRepository = priceHistoryRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public void deleteItem(String itemName) {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            //Удаляем все записи в истории записи цен
            List<PriceHistory> priceHistory = priceHistoryRepository.findByItemName(itemName);
            for (PriceHistory priceHistoryItem : priceHistory) {
                priceHistoryRepository.delete(itemName);
            }

            //Удаляем у всех пользователей запись о добавлении данного итема в избранное
            favoriteRepository.deleteAllByItemName(itemName);

            //Удаляем предмет
            itemRepository.deleteItemByName(itemName);

            transactionManager.commit(transaction);
        } catch (DataAccessException e) {
            transactionManager.rollback(transaction);
            throw e;
        }
    }
}
