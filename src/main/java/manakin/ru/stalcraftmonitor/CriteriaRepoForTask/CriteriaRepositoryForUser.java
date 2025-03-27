package manakin.ru.stalcraftmonitor.CriteriaRepoForTask;

import manakin.ru.stalcraftmonitor.Entities.Item;
import manakin.ru.stalcraftmonitor.Entities.User;

import java.util.List;

interface userRepositoryCustom {
    /**
     * Находит пользователя с заданной почтой
     *
     * @param email электорнная почта пользователя
     */

    List<User> findByEmail(String email);

    /**
     * Находит все избранные предметы по email-у пользователя
     *
     * @param email электорнная почта пользователя
     */
    List<Item> findFavoriteItemsByEmail(String email);
}
