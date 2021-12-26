package jh.ParkingService.repository.review;

import jh.ParkingService.domain.Review;
import jh.ParkingService.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Primary
@Transactional
@Repository
public class JpaReviewDataRepository implements ReviewDataRepository{

    private final EntityManager em;

    @Autowired
    public JpaReviewDataRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Review review) {
        em.persist(review);
    }

    @Override
    public void delete(int reviewUid) {
        Review review = findByReviewUid(reviewUid);

        em.remove(review);
    }

    @Override
    public void update(int reviewUid, String reviewImageUrl, String reviewText, Short reviewRate, String reviewerNickName) {
        Query q = em.createQuery("UPDATE Review r SET r.reviewImageUrl = ?1, r.reviewText = ?2, r.reviewRate = ?3, r.reviewerNickName = ?4 WHERE r.reviewUid = ?5")
                .setParameter(1, reviewImageUrl)
                .setParameter(2, reviewText)
                .setParameter(3, reviewRate)
                .setParameter(4, reviewRate)
                .setParameter(5, reviewUid);

        q.executeUpdate();
    }

    @Override
    public List<Review> findByParkCode(String parkCode) {
        return em.createQuery("select r from Review r where r.parkCode = ?1")
                .setParameter(1, parkCode)
                .getResultList();
    }

    @Override
    public Review findByReviewUid(int reviewUid) {
        return em.find(Review.class, reviewUid);
    }


}
