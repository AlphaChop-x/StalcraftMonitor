package manakin.ru.stalcraftmonitor.service;

import manakin.ru.stalcraftmonitor.entity.Item;

public interface ItemService {
    /**
     * Создаёт предмет
     *
     * @param item - предмет
     * @return
     */

    Item createItem(Item item);

    /**
     * Удаляет предмет по его имени
     *
     * @param itemName имя предмета
     */
    void deleteItem(String itemName);

    /**
     * Возвращает предмет Item по его ID
     * <p>
     * Item {id,name,category,description}
     *
     * @param itemId айди предмета
     */
    Item getItem(String itemId);
}
