package jh.ParkingService.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@ToString
@Table(name = "BASE_USER")
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



}
