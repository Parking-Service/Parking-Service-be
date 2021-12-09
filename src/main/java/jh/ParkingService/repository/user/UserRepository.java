package jh.ParkingService.repository.user;

import jh.ParkingService.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User member);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
