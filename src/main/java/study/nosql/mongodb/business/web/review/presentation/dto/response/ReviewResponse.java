package study.nosql.mongodb.business.web.review.presentation.dto.response;

import study.nosql.mongodb.business.web.image.presentation.dto.response.ReviewImageResponse;
import study.nosql.mongodb.common.documents.image.document.ReviewImageDocument;
import study.nosql.mongodb.common.documents.review.document.ReviewDocument;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReviewResponse {

    private final Long reviewId;
    private final Long postId;
    private final String content;
    private final List<ReviewImageResponse> reviewImages;

    public ReviewResponse(ReviewDocument reviewDocument) {
        this.reviewId = reviewDocument.getReviewId();
        this.postId = reviewDocument.getPostId();
        this.content = reviewDocument.getContent();
        this.reviewImages = convertToResponse(reviewDocument.getReviewImageDocument());
    }

    private List<ReviewImageResponse> convertToResponse(List<ReviewImageDocument> reviewImageDocument) {
        if (Objects.isNull(reviewImageDocument) || reviewImageDocument.isEmpty()) {
            return Collections.emptyList();
        }
        return reviewImageDocument.stream()
                .map(ReviewImageResponse::new)
                .collect(Collectors.toList());
    }

    public Long getReviewId() {
        return reviewId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public List<ReviewImageResponse> getReviewImages() {
        return reviewImages;
    }

    @Override
    public String toString() {
        return String.format("reviewId: %s", reviewId);
    }
}
