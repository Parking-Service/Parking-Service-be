package jh.ParkingService.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "REVIEW")
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "reviewUid") //리뷰 UID
    private int reviewUid;

    @Column(name = "reviewerUid")   //리뷰어 UID
    private String reviewerUid;

    @Column(name = "parkCode")  //주차장코드
    private String parkCode;

    @Column(name = "reviewerNickName")  //리뷰어 NICKNAME
    private String reviewerNickName;

    @Column(name = "reviewImageUrl1")    //리뷰이미지URL(이미지로 받아서 서버에서 저장)
    @Nullable
    private String reviewImageUrl1;

    @Column(name = "reviewImageUrl2")
    @Nullable
    private String reviewImageUrl2;

    @Column(name = "reviewImageUrl3")
    @Nullable
    private String reviewImageUrl3;

    @Column(name = "reviewImageUrl4")
    @Nullable
    private String reviewImageUrl4;

    @Column(name = "reviewImageUrl5")
    @Nullable
    private String reviewImageUrl5;

    @Column(name = "reviewText")    //리뷰내용
    private String reviewText;

    @Column(name = "reviewDate")    //리뷰 날짜(서버에서 저장)
    private String reviewDate;

    @Column(name = "likeCount")     //리뷰 좋아요 갯수
    private Short likeCount;

    @Column(name = "reviewRate")    //리뷰 평점
    private Short reviewRate;


    public Review(String reviewerUid, String parkCode, String reviewerNickName, @Nullable String reviewImageUrl1, @Nullable String reviewImageUrl2, @Nullable String reviewImageUrl3, @Nullable String reviewImageUrl4, @Nullable String reviewImageUrl5, String reviewText, String reviewDate, Short likeCount, Short reviewRate) {
        this.reviewerUid = reviewerUid;
        this.parkCode = parkCode;
        this.reviewerNickName = reviewerNickName;
        this.reviewImageUrl1 = reviewImageUrl1;
        this.reviewImageUrl2 = reviewImageUrl2;
        this.reviewImageUrl3 = reviewImageUrl3;
        this.reviewImageUrl4 = reviewImageUrl4;
        this.reviewImageUrl5 = reviewImageUrl5;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.likeCount = likeCount;
        this.reviewRate = reviewRate;
    }

    public Review(String reviewerUid, String parkCode, String reviewerNickName, String reviewText, String reviewDate, Short likeCount, Short reviewRate) {
        this.reviewerUid = reviewerUid;
        this.parkCode = parkCode;
        this.reviewerNickName = reviewerNickName;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.likeCount = likeCount;
        this.reviewRate = reviewRate;
    }
}
