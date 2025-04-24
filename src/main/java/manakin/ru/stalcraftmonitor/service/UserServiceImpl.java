package manakin.ru.stalcraftmonitor.service;

import manakin.ru.stalcraftmonitor.entity.UserEntity;
import manakin.ru.stalcraftmonitor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import manakin.ru.stalcraftmonitor.entity.Role;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity entity = userRepository.findByEmailIgnoreCase(email);

        if (entity == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found!");
        }

        return new User(entity.getEmail(), entity.getPassword(), extractRoles(entity));
    }

    @Override
    public UserEntity registerUser(String username, String email, String password) {
        UserEntity userEntity = new UserEntity();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userEntity.setName(username);
        userEntity.setEmail(email);
        userEntity.setPassword(encoder.encode(password));
        userEntity.setRole(Role.USER);

        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public void updateUser(UserEntity user) {

    }

    @Override
    public void deleteUser(UserEntity user) {

    }

    private Collection<? extends GrantedAuthority> extractRoles(UserEntity entity) {
        return entity.getRole().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toSet());
    }
}
