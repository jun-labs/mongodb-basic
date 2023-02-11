package study.nosql.mongodb.business.domain.image.entity;

import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.common.mapping.Deleted;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reviewimage")
public class ReviewImage {

    @Id
    private Long reviewImageId;

    @Column
    private Long postId;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastModifiedAt;

    @Enumerated(EnumType.STRING)
    private Deleted deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 image 패키지 외부에서 호출하지 말 것.
     */
    protected ReviewImage() {
    }

    public ReviewImage(
            Long postId,
            String imageUrl
    ) {
        validate(postId, imageUrl);
        this.postId = postId;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = null;
        this.deleted = Deleted.FALSE;
    }

    private void validate(
            Long postId,
            String imageUrl
    ) {
        if (Objects.isNull(postId)) {
            throw new IllegalArgumentException("게시글 아이디를 입력해주세요.");
        }
        if (Objects.isNull(imageUrl) || imageUrl.isBlank()) {
            throw new IllegalArgumentException("이미지 URL을 입력해주세요.");
        }
        if (imageUrl.length() > 1000) {
            throw new IllegalArgumentException("입력 가능한 URL 최대길이를 초과했습니다.");
        }
    }

    public Long getReviewImageId() {
        return reviewImageId;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getReviewId() {
        return review.getReviewId();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewImage that)) return false;
        return getReviewImageId().equals(that.getReviewImageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReviewImageId());
    }

    @Override
    public String toString() {
        return reviewImageId.toString();
    }
}
