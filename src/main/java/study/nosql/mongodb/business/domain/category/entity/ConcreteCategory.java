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
@Table(name = "concretecategory")
public class ConcreteCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concreteCategoryId;

    @Column
    private String name;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 category 패키지 외부에서 호출하지 말 것.
     */
    protected ConcreteCategory() {
    }

    public ConcreteCategory(String name) {
        validate(name);
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = null;
    }

    private void validate(String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("서브 카테고리 이름을 입력해주세요.");
        }
        if (name.length() > 20) {
            throw new IllegalArgumentException("입력 가능한 최대 이름 길이를 초과했습니다.");
        }
    }

    public Long getConcreteCategoryId() {
        return concreteCategoryId;
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

    public SubCategory getSubCategory() {
        return subCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConcreteCategory that)) return false;
        return getConcreteCategoryId().equals(that.getConcreteCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConcreteCategoryId());
    }

    @Override
    public String toString() {
        return String.format("subCategoryId: %s, name: %s", concreteCategoryId, name);
    }
}
