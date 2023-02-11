package study.nosql.mongodb.business.domain.review.entity;

import study.nosql.mongodb.common.mapping.Deleted;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column
    private Long memberId;

    @Column
    private Long postId;

    @Column
    private String content;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastModifiedAt;

    @Enumerated(EnumType.STRING)
    private Deleted deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 review 패키지 외부에서 호출하지 말 것.
     */
    protected Review() {
    }

    public Review(
            Long memberId,
            Long postId,
            String content
    ) {
        validate(postId, content);
        this.memberId = memberId;
        this.postId = postId;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = null;
        this.deleted = Deleted.FALSE;
    }

    private void validate(
            Long postId,
            String content
    ) {
        if (Objects.isNull(postId)) {
            throw new IllegalArgumentException("게시글 아이디를 입력해주세요.");
        }
        if (Objects.isNull(content) || content.isBlank()) {
            throw new IllegalArgumentException("게시글 내용을 입력해주세요.");
        }
        if (content.length() > 2000) {
            throw new IllegalArgumentException("입력 가능한 리뷰 내용의 최대길이를 초과했습니다.");
        }
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

    public void update(
            Long memberId,
            Long postId,
            String content
    ) {
        if (!this.memberId.equals(memberId)) {
            throw new RuntimeException("권한이 존재하지 않습니다.");
        }
        if (!this.postId.equals(postId)) {
            throw new RuntimeException("잘못된 요청입니다.");
        }
        this.content = content;
    }

    public void delete(Long memberId) {
        if (!this.memberId.equals(memberId)) {
            throw new RuntimeException("권한이 존재하지 않습니다.");
        }
        this.deleted = Deleted.TRUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return getReviewId().equals(review.getReviewId());
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
