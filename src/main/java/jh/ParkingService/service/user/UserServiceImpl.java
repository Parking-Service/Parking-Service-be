package jh.ParkingService.service.user;

import jh.ParkingService.domain.user.User;
import jh.ParkingService.domain.user.UserRepository;
import jh.ParkingService.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    //user데이터 DB에 저장
    public void join(UserDto userDto){
        userRepository.save(userDto.toEntity());
    }

    public Optional<User> findUserByUid(String uid) { return userRepository.findByUid(uid); }

    //email주소로 db에서 유저데이터 검색
    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public String findUserNickName(String uid) { return userRepository.findNicknameByUid(uid); }

    public List<User> findAll() {return userRepository.findAll(); }
}
