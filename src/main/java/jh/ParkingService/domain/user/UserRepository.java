package jh.ParkingService.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    Optional<User> findByUid(String uid);

    Optional<User> findByEmail(String email);

    @Query("select u.nickname from User u where u.uid = ?1")
    String findNicknameByUid(String uid);

    List<User> findAll();
}
