package jh.ParkingService.domain.user;

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
    private String uid;
    @Column(length = 50, nullable = true)
    private String email;
    @Column(length = 10, nullable = false)
    private String logintype;
    @Column(length = 50, nullable = false)
    private String nickname;

}
