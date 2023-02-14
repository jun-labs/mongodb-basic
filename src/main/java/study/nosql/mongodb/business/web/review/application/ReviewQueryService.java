package study.nosql.mongodb.business.web.review.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.nosql.mongodb.business.domain.common.exception.BusinessException;
import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.business.domain.review.infrastructure.query.ReviewQueryDslQueryRepository;

import java.util.List;

import static study.nosql.mongodb.business.domain.common.exception.domain.review.ReviewExceptionType.REVIEW_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewQueryDslQueryRepository reviewQueryRepository;

    @Transactional
    public Review findReviewById(Long reviewId){
        return reviewQueryRepository.findReviewById(reviewId)
                .orElseThrow(()-> BusinessException.of(REVIEW_NOT_FOUND_EXCEPTION));
    }

    @Transactional(readOnly = true)
    public List<Review> findReviewsByPostId(Long postId) {
        return reviewQueryRepository.findReviewsByPostId(postId);
    }
}
