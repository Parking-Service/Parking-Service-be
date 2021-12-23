package jh.ParkingService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jh.ParkingService.domain.User;
import jh.ParkingService.service.user.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public void register(@RequestBody User user){
        System.out.println(user.toString());
        userService.join(user);
    }
}
