package jh.ParkingService.repository.review;

import jh.ParkingService.domain.Review;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
}
