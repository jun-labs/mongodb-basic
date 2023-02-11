package study.nosql.mongodb.common.documents.review.document;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.common.documents.image.document.ReviewImageDocument;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

@Document(collation = "review")
public class ReviewDocument {

    @Id
    private String _id;

    @Column
    private Long reviewId;

    @Column
    private Long postId;

    @Column
    private String content;

    @Column
    private List<ReviewImageDocument> reviewImageDocument;

    /**
     * @Nullary-Constructor. MongoDB 기본 생성자로 review document 패키지 외부에서 호출하지 말 것.
     */
    private ReviewDocument() {
    }

    public ReviewDocument(
            Review review,
            List<ReviewImageDocument> reviewImageDocument
    ) {
        this._id = new ObjectId().toHexString();
        this.reviewId = review.getReviewId();
        this.postId = review.getPostId();
        this.content = review.getContent();
        this.reviewImageDocument = reviewImageDocument;
    }

    public ReviewDocument(Review review) {
        this.reviewId = review.getReviewId();
        this.postId = review.getPostId();
        this.content = review.getContent();
    }

    public String get_id() {
        return _id;
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

    public List<ReviewImageDocument> getReviewImageDocument() {
        return reviewImageDocument;
    }

    public void update(Review review) {
        this.reviewId = review.getReviewId();
        this.postId = review.getPostId();
        this.content = review.getContent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewDocument that)) return false;
        return getReviewId().equals(that.getReviewId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReviewId());
    }

    @Override
    public String toString() {
        return reviewId.toString();
    }
}
