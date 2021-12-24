package jh.ParkingService.repository.user;

import jh.ParkingService.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Primary
@Transactional
@Repository
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    @Autowired
    public JpaUserRepository(EntityManager em){
        this.em = em;
    }



    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public Optional<User> findByUid(String uid) {
        User user = em.find(User.class, uid);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = em.find(User.class, email);
        return Optional.ofNullable(user);
    }


    @Override
    public List<User> findAll() {
        return em.createQuery("select m from BASE_USER m", User.class)
                .getResultList();
    }
}
