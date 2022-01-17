package jh.ParkingService.controller;

import io.swagger.annotations.ApiOperation;
import jh.ParkingService.domain.user.User;
import jh.ParkingService.dto.UserDto;
import jh.ParkingService.service.user.UserServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    @ApiOperation(value = "유저 회원등록", notes = "유저 정보를 입력받아 회원등록을 하고, 이미 등록된 유저이면 등록하지 않는다.")
    public void register(@RequestBody UserDto userDto){

        try{
            System.out.println(userDto.toString());
            userService.join(userDto);
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미 등록된 회원입니다.");
        }
    }

    @GetMapping("all")
    @ApiOperation(value = "전체 유저 정보 찾기", notes = "전체 유저 데이터를 찾아 리턴한다.")
    public List<User> findAll(){
        return userService.findAll();
    }
}
