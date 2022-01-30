package jh.ParkingService.repository.likeReview;

import jh.ParkingService.domain.LikeReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class JpaLikeReviewRepository implements LikeReviewRepository {

    private final EntityManager em;

    @Autowired
    public JpaLikeReviewRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void firstLikeCheck(int likeReviewUid, String likeUserUid) {
        try {
            LikeReview likeReview = em.find(LikeReview.class, likeReviewUid + likeUserUid);
            System.out.println("not First Like this Review");
        } catch (NullPointerException e) {
            LikeReview likeReview = new LikeReview(likeReviewUid + likeUserUid, likeReviewUid, likeUserUid);

            em.persist(likeReview);
            System.out.println("First Like this Review");
        }
    }

    @Override
    public void like(int likeReviewUid, String likeUserUid, boolean like) {

        em.createQuery("UPDATE Like_Review l SET l.likeCheck = ?1 WHERE l.likeId = ?2")
                .setParameter(1, like)
                .setParameter(2, likeReviewUid + likeUserUid)
                .executeUpdate();

        if (like == true) {
            em.createQuery("UPDATE Review r SET r.like = r.like + 1 WHERE r.reviewUid = ?1")
                    .setParameter(1, likeReviewUid)
                    .executeUpdate();
        } else {
            em.createQuery("UPDATE Review r SET r.like = r.like - 1 WHERE r.reviewUid = ?1")
                    .setParameter(1, likeReviewUid)
                    .executeUpdate();
        }
    }

    @Override
    public void removeLike(int reviewUid) {

        em.createQuery("DELETE FROM Like_Review l WHERE l.likeReviewUid = ?1")
                .setParameter(1, reviewUid)
                .executeUpdate();
    }

}
