package jh.ParkingService.dto;

import jh.ParkingService.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {

    private String uid;
    private String email;
    private String nickname;
    private String logintype;

    public User toEntity(){
        return User.builder()
                .uid(uid)
                .email(email)
                .nickname(nickname)
                .logintype(logintype)
                .build();
    }
}
