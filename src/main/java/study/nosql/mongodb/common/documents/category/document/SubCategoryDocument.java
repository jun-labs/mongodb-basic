package study.nosql.mongodb.common.documents.category.document;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import study.nosql.mongodb.business.domain.category.entity.ConcreteCategory;
import study.nosql.mongodb.business.domain.category.entity.SubCategory;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Objects;

@Document(collation = "sub_category")
public class SubCategoryDocument {

    @Id
    private String _id;

    @Column
    private Long subCategoryId;

    @Column
    private String name;

    @Column
    private ConcreteCategoryDocument concreteCategory;

    /**
     * @Nullary-Constructor. MongoDB 기본 생성자로 category document 패키지 외부에서 호출하지 말 것.
     */
    private SubCategoryDocument() {
    }

    public SubCategoryDocument(
            SubCategory subCategory,
            ConcreteCategory concreteCategory
    ) {
        this._id = new ObjectId().toHexString();
        this.subCategoryId = subCategory.getSubCategoryId();
        this.name = subCategory.getName();
        this.concreteCategory = new ConcreteCategoryDocument(concreteCategory);
    }

    public String get_id() {
        return _id;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public String getName() {
        return name;
    }

    public ConcreteCategoryDocument getConcreteCategory() {
        return concreteCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubCategoryDocument that)) return false;
        return getSubCategoryId().equals(that.getSubCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubCategoryId());
    }

    @Override
    public String toString() {
        return """
                {
                    "_id": %s,
                    "subCategoryId": %s,
                    "name": %s,
                    "concreteCategory": %s
                }
                """.formatted(
                _id,
                subCategoryId,
                name,
                concreteCategory
        );
    }
}
