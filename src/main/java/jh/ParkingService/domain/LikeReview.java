package jh.ParkingService.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "LIKE_REVIEW")
public class LikeReview {
    @Id
    @Column(name = "likeId")
    private String likeId;

    @Column(name = "likeReviewUid")
    private int likeReviewUid;

    @Column(name = "likeUserUid")
    private String likeUserUid;

    @Column(name = "likeCheck")
    private boolean likeCheck;


    public LikeReview(String likeId, int likeReviewUid, String likeUserUid) {
        this.likeId = likeId;
        this.likeReviewUid = likeReviewUid;
        this.likeUserUid = likeUserUid;
    }
}
