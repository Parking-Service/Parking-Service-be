package jh.ParkingService.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review save(Review review);

    @Query("delete from Review r where r.reviewUid = ?1")
    void delete(int reviewUid);

    List<Review> findByParkCode(String parkCode);

    List<Review> findTop5ByParkCodeOrderByLikeCount(String parkCode);

    Optional<Review> findByReviewUid(int reviewUid);
}
