package study.nosql.mongodb.business.web.category.presentation.dto.response;

import study.nosql.mongodb.common.documents.category.document.ConcreteCategoryDocument;

public class ConcreteCategoryResponse {

    private Long concreteCategoryId;
    private String name;

    public ConcreteCategoryResponse(ConcreteCategoryDocument concreteCategory) {
        this.concreteCategoryId = concreteCategory.getConcreteCategoryId();
        this.name = concreteCategory.getName();
    }

    public Long getConcreteCategoryId() {
        return concreteCategoryId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return """
                "concreteCategoryId": "%s",
                "name": %s
                """.formatted(concreteCategoryId, name);
    }
}
