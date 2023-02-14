package study.nosql.mongodb.business.web.review.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.business.domain.review.infrastructure.command.ReviewJpaRepository;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    private final ReviewJpaRepository reviewJpaRepository;

    @Transactional
    public Review writeReview(Long memberId, Long postId, String content) {
        Review newReview = new Review(memberId, postId, content);
        return reviewJpaRepository.save(newReview);
    }

    @Transactional
    public Review updateReview(Long memberId, Long postId, Long reviewId, String content) {
        Review findReview = reviewJpaRepository.findById(reviewId).orElseThrow();
        findReview.update(memberId, postId, content);
        return findReview;
    }

    @Transactional
    public void deleteReview(Long memberId,
                             Review review) {
        review.delete(memberId);
    }
}
