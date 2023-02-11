package study.nosql.mongodb.common.documents.category.document;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import study.nosql.mongodb.business.domain.category.entity.ConcreteCategory;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Objects;

@Document("concrete_category_document")
public class ConcreteCategoryDocument {

    @Id
    private String _id;

    @Column
    private Long concreteCategoryId;

    @Column
    private String name;

    /**
     * @Nullary-Constructor. MongoDB 기본 생성자로 category document 패키지 외부에서 호출하지 말 것.
     */
    private ConcreteCategoryDocument() {
    }

    public ConcreteCategoryDocument(ConcreteCategory concreteCategory) {
        this._id = new ObjectId().toHexString();
        this.concreteCategoryId = concreteCategory.getConcreteCategoryId();
        this.name = concreteCategory.getName();
    }

    public String get_id() {
        return _id;
    }

    public Long getConcreteCategoryId() {
        return concreteCategoryId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConcreteCategoryDocument that)) return false;
        return getConcreteCategoryId().equals(that.getConcreteCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConcreteCategoryId());
    }

    @Override
    public String toString() {
        return """
                {
                    "_id": %s,
                    "concreteCategoryId": %s,
                    "name": %s
                }
                """.formatted(
                _id,
                concreteCategoryId,
                name
        );
    }
}
