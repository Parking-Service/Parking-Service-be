package jh.ParkingService.repository.review;

import jh.ParkingService.domain.Park;
import jh.ParkingService.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Primary
@Transactional
@Repository
public class JpaReviewDataRepository implements ReviewDataRepository {

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
        em.createQuery("UPDATE Review r SET r.reviewImageUrl = ?1, r.reviewText = ?2, r.reviewRate = ?3, r.reviewerNickName = ?4 WHERE r.reviewUid = ?5")
                .setParameter(1, reviewImageUrl)
                .setParameter(2, reviewText)
                .setParameter(3, reviewRate)
                .setParameter(4, reviewerNickName)
                .setParameter(5, reviewUid)
                .executeUpdate();

    }

    @Override
    public void update(int reviewUid, String reviewText, Short reviewRate, String reviewerNickName) {
        em.createQuery("UPDATE Review r SET r.reviewText = ?1, r.reviewRate = ?2, r.reviewerNickName = ?3 WHERE r.reviewUid = ?4")
                .setParameter(1, reviewText)
                .setParameter(2, reviewRate)
                .setParameter(3, reviewerNickName)
                .setParameter(4, reviewUid)
                .executeUpdate();
    }

    @Override
    public List<Review> findAllByParkCode(String parkCode) {
        return em.createQuery("select r from Review r where r.parkCode = ?1")
                .setParameter(1, parkCode)
                .getResultList();
    }

    @Override
    public List<Review> findTop5ByParkCode(String parkCode) {
       return em.createQuery("select r from Review r where r.parkCode = ?1 order by r.likeCount")
                .setParameter(1, parkCode)
                .setMaxResults(5)
                .getResultList();
        
    }

    @Override
    public Review findByReviewUid(int reviewUid) {
        return em.find(Review.class, reviewUid);
    }


}
