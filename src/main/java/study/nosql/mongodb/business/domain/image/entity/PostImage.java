package study.nosql.mongodb.business.domain.image.entity;

import study.nosql.mongodb.common.mapping.Deleted;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "postimage")
public class PostImage {

    @Id
    private Long postImageId;

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

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 image 패키지 외부에서 호출하지 말 것.
     */
    protected PostImage() {
    }

    public PostImage(
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

    public Long getPostImageId() {
        return postImageId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostImage postImage)) return false;
        return getPostImageId().equals(postImage.getPostImageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostImageId());
    }

    @Override
    public String toString() {
        return imageUrl;
    }
}
