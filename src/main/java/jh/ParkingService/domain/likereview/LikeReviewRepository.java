package jh.ParkingService.domain.likereview;

public interface LikeReviewRepository {
    void firstLikeCheck(int likeReviewUid, String likeUserUid);
    void like(int likeReviewUid, String likeUserUid, boolean like);
    void removeLike(int reviewUid);

}
