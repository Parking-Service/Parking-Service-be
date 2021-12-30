package jh.ParkingService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jh.ParkingService.domain.User;
import jh.ParkingService.service.user.UserServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;


@RestController
@RequestMapping("user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public void register(@RequestBody User user){

        try{
            System.out.println(user.toString());
            userService.join(user);
        }catch (DataIntegrityViolationException e){
            System.out.println("이미 등록된 회원입니다.");
        }

    }
}
