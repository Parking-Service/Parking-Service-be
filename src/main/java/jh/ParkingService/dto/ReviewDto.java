package jh.ParkingService.dto;

import jh.ParkingService.domain.Review;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReviewDto {

    private int reviewUid;
    private String reviewerUid;
    private String parkCode;
    private String reviewerNickName;
    private Float reviewRate;
    private Long reviewDate;
    private String reviewText;
    private Short likeCount;
    private String reviewImageUrl;

    public ReviewDto(String reviewerUid, String parkCode, String reviewerNickName, String reviewImageUrl, String reviewText, Long reviewDate, Short likeCount, Float reviewRate) {
        this.reviewerUid = reviewerUid;
        this.parkCode = parkCode;
        this.reviewerNickName = reviewerNickName;
        this.reviewImageUrl = reviewImageUrl;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.likeCount = likeCount;
        this.reviewRate = reviewRate;
    }

    public ReviewDto(String reviewerUid, String parkCode, String reviewerNickName, String reviewText, Long reviewDate, Short likeCount, Float reviewRate) {
        this.reviewerUid = reviewerUid;
        this.parkCode = parkCode;
        this.reviewerNickName = reviewerNickName;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.likeCount = likeCount;
        this.reviewRate = reviewRate;
    }

    public Review toEntity_ExistImage(){
        return Review.builder()
                .reviewerUid(reviewerUid)
                .parkCode(parkCode)
                .reviewerNickName(reviewerNickName)
                .reviewImageUrl(reviewImageUrl)
                .reviewText(reviewText)
                .reviewDate(reviewDate)
                .likeCount(likeCount)
                .reviewRate(reviewRate)
                .build();
    }

    public Review toEntity_NoneImage(){
        return Review.NoneImageBuilder()
                .reviewerUid(reviewerUid)
                .parkCode(parkCode)
                .reviewerNickName(reviewerNickName)
                .reviewText(reviewText)
                .reviewDate(reviewDate)
                .likeCount(likeCount)
                .reviewRate(reviewRate)
                .build();
    }


}
