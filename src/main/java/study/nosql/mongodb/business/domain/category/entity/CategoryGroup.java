package study.nosql.mongodb.business.domain.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "categorygroup")
public class CategoryGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryGroupId;

    @Column
    private String name;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastModifiedAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 category 패키지 외부에서 호출하지 말 것.
     */
    protected CategoryGroup() {
    }

    public CategoryGroup(String name) {
        validate(name);
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = null;
    }

    private void validate(String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("카테고리 이름을 입력해주세요.");
        }
        if (name.length() > 20) {
            throw new IllegalArgumentException("입력 가능한 최대 이름 길이를 초과했습니다.");
        }
    }

    public Long getCategoryGroupId() {
        return categoryGroupId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryGroup categoryGroup)) return false;
        return getCategoryGroupId().equals(categoryGroup.getCategoryGroupId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryGroupId());
    }

    @Override
    public String toString() {
        return String.format("categoryId: %s, name: %s", categoryGroupId, name);
    }
}
