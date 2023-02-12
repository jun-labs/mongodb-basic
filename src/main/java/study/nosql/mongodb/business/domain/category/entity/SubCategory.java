package study.nosql.mongodb.business.domain.category.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "subcategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;

    @Column
    private String name;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category_group_id")
    private CategoryGroup categoryGroup;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 category 패키지 외부에서 호출하지 말 것.
     */
    protected SubCategory() {
    }

    public SubCategory(
            Long postId,
            CategoryGroup categoryGroup,
            String name
    ) {
        validate(postId, categoryGroup, name);
        this.categoryGroup = categoryGroup;
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = null;
    }

    private void validate(Long postId, CategoryGroup categoryGroupId, String name) {
        if (Objects.isNull(postId) || Objects.isNull(categoryGroupId)) {
            throw new IllegalArgumentException("올바른 상위 타입을 입력해주세요.");
        }
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("서브 카테고리 이름을 입력해주세요.");
        }
        if (name.length() > 20) {
            throw new IllegalArgumentException("입력 가능한 최대 이름 길이를 초과했습니다.");
        }
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public Long getCategoryId() {
        return categoryGroup.getCategoryGroupId();
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public CategoryGroup getCategoryGroup() {
        return categoryGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubCategory that)) return false;
        return getSubCategoryId().equals(that.getSubCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubCategoryId());
    }

    @Override
    public String toString() {
        return String.format("subCategoryId: %s, name: %s", subCategoryId, name);
    }
}
