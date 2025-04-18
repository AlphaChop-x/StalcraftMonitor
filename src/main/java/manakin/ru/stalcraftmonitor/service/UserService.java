package manakin.ru.stalcraftmonitor.service;

import manakin.ru.stalcraftmonitor.entity.UserEntity;

public interface UserService {
    UserEntity registerUser(String username, String email, String password);

    void updateUser(UserEntity user);

    void deleteUser(UserEntity user);
}
