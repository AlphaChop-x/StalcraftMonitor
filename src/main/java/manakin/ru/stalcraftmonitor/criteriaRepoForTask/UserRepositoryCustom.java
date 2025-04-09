package manakin.ru.stalcraftmonitor.criteriaRepoForTask;

import manakin.ru.stalcraftmonitor.entity.Item;
import manakin.ru.stalcraftmonitor.entity.User;

import java.util.List;

interface UserRepositoryCustom {
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
