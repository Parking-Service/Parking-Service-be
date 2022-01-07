package jh.ParkingService.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Table(name = "BASE_USER")
@NoArgsConstructor
@Entity
public class User {

    @Id
    @Column(name = "uid")
    private String uid;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "logintype")
    private String logintype;

    @Builder
    public User(String uid, String email, String nickname, String logintype) {
        this.uid = uid;
        this.email = email;
        this.nickname = nickname;
        this.logintype = logintype;
    }
}
