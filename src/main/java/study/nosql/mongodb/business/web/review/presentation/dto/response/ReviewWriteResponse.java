package study.nosql.mongodb.business.web.review.presentation.dto.response;

import study.nosql.mongodb.business.domain.review.entity.Review;

public class ReviewWriteResponse {

    private final Long reviewId;

    public ReviewWriteResponse(Review review) {
        this.reviewId = review.getReviewId();
    }

    public static ReviewWriteResponse of(Review review) {
        return new ReviewWriteResponse(review);
    }

    public Long getReviewId() {
        return reviewId;
    }

    @Override
    public String toString() {
        return reviewId.toString();
    }
}
