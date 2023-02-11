package study.nosql.mongodb.business.domain.post.entity;

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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private Long categoryGroupId;

    @Column
    private Long subCategoryId;

    @Column
    private Long concreteCategoryId;

    @Column
    private Long memberId;

    @Column
    private String content;

    @Column
    private Integer comentCount;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastModifiedAt;

    @Enumerated(EnumType.STRING)
    private Deleted deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 post 패키지 외부에서 호출하지 말 것.
     */
    protected Post() {
    }

    public Post(
            Long memberId,
            String content
    ) {
        validate(memberId, content);
        this.memberId = memberId;
        this.content = content;
        this.comentCount = 0;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = null;
        this.deleted = Deleted.FALSE;
    }

    private void validate(
            Long memberId,
            String content
    ) {
        if (Objects.isNull(memberId)) {
            throw new IllegalArgumentException("회원 아이디를 입력해주세요.");
        }
        if (Objects.isNull(content) || content.isBlank()) {
            throw new IllegalArgumentException("게시글 내용을 입력해주세요.");
        }
        if (content.length() > 1000) {
            throw new IllegalArgumentException("입력 가능한 게시글 내용의 최대길이를 초과했습니다.");
        }
    }

    public Long getPostId() {
        return postId;
    }

    public Long getCategoryGroupId() {
        return categoryGroupId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public Long getConcreteCategoryId() {
        return concreteCategoryId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getContent() {
        return content;
    }

    public Integer getComentCount() {
        return comentCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public Deleted getDeleted() {
        return deleted;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return postId.equals(post.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

    @Override
    public String toString() {
        return postId.toString();
    }
}
