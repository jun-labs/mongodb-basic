package study.nosql.mongodb.common.documents.image.document;

import org.springframework.data.mongodb.core.mapping.Document;
import study.nosql.mongodb.business.domain.image.entity.ReviewImage;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Objects;

@Document(collation = "review_image")
public class ReviewImageDocument {

    @Id
    private Long _id;

    @Column
    private Long reviewImageId;

    @Column
    private Long postId;

    @Column
    private Long reviewId;

    @Column
    private String imageUrl;

    /**
     * @Nullary-Constructor. MongoDB 기본 생성자로 image document 패키지 외부에서 호출하지 말 것.
     */
    private ReviewImageDocument() {
    }

    public ReviewImageDocument(ReviewImage reviewImage) {
        this.reviewImageId = reviewImage.getReviewImageId();
        this.postId = reviewImage.getPostId();
        this.reviewId = reviewImage.getReviewId();
        this.imageUrl = reviewImage.getImageUrl();
    }

    public Long get_id() {
        return _id;
    }

    public Long getReviewImageId() {
        return reviewImageId;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewImageDocument that)) return false;
        return getReviewImageId().equals(that.getReviewImageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReviewImageId());
    }

    @Override
    public String toString() {
        return _id.toString();
    }
}
