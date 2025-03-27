package manakin.ru.stalcraftmonitor.CriteriaRepoForTask;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import manakin.ru.stalcraftmonitor.Entities.Favorite;
import manakin.ru.stalcraftmonitor.Entities.Item;
import manakin.ru.stalcraftmonitor.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryCustom implements userRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public UserRepositoryCustom(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public List<User> findByEmail(String email) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate namePredicate = criteriaBuilder.equal(userRoot.get("email"), email);

        criteriaQuery.select(userRoot).where(namePredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Item> findFavoriteItemsByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);

        Root<User> userRoot = query.from(User.class);
        Join<User, Favorite> favoriteJoin = userRoot.join("favorites", JoinType.INNER);
        Join<Favorite, Item> itemJoin = favoriteJoin.join("item", JoinType.INNER);

        query.where(criteriaBuilder.equal(userRoot.get("email"), email));

        query.select(itemJoin);

        return entityManager.createQuery(query).getResultList();
    }
}
