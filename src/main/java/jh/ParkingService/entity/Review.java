package jh.ParkingService.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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

    @Column(name = "reviewImageUrl", nullable = true)    //리뷰이미지URL(이미지로 받아서 서버에서 저장)
    private String reviewImageUrl;

    @Column(name = "reviewText")    //리뷰내용
    private String reviewText;

    @Column(name = "reviewDate")    //리뷰 날짜(서버에서 저장)
    private String reviewDate;

    @Column(name = "likeCount")     //리뷰 좋아요 갯수
    private Short likeCount;

    @Column(name = "reviewRate")    //리뷰 평점
    private Short reviewRate;

    @Builder
    public Review(String reviewerUid, String parkCode, String reviewerNickName, String reviewImageUrl, String reviewText, String reviewDate, Short likeCount, Short reviewRate) {
        this.reviewerUid = reviewerUid;
        this.parkCode = parkCode;
        this.reviewerNickName = reviewerNickName;
        this.reviewImageUrl = reviewImageUrl;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.likeCount = likeCount;
        this.reviewRate = reviewRate;
    }

    @Builder(builderMethodName = "NoneImageBuilder")
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
