package study.nosql.mongodb.business.web.category.presentation.dto.response;

import study.nosql.mongodb.common.documents.category.document.SubCategoryDocument;

public class SubCategoryResponse {

    private Long subCategoryId;
    private String name;
    private ConcreteCategoryResponse concreteCategoryResponse;

    public SubCategoryResponse(SubCategoryDocument subCategoryDocument) {
        this.subCategoryId = subCategoryDocument.getSubCategoryId();
        this.name = subCategoryDocument.getName();
        this.concreteCategoryResponse = new ConcreteCategoryResponse(subCategoryDocument.getConcreteCategory());
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public String getName() {
        return name;
    }

    public ConcreteCategoryResponse getConcreteCategoryResponse() {
        return concreteCategoryResponse;
    }
}
