package jh.ParkingService.repository.user;

import jh.ParkingService.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByUid(String uid);
    Optional<User> findByEmail(String email);
    String findNickName(String uid);
    List<User> findAll();
}
