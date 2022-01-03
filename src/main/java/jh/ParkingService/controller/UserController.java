package jh.ParkingService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "유저 회원등록", notes = "유저 정보를 입력받아 회원등록을 하고, 이미 등록된 유저이면 등록하지 않는다.")
    public void register(@RequestBody User user){

        try{
            System.out.println(user.toString());
            userService.join(user);
        }catch (DataIntegrityViolationException e){
            System.out.println("이미 등록된 회원입니다.");
        }

    }
}
