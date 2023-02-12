package study.nosql.mongodb.business.web.category.presentation.dto.response;

import study.nosql.mongodb.common.documents.category.document.CategoryDocument;

public class CategoryResponse {

    private Long categoryGroupId;
    private String name;
    private SubCategoryResponse subCategoryResponse;

    public CategoryResponse(CategoryDocument categoryDocument) {
        this.categoryGroupId = categoryDocument.getCategoryGroupId();
        this.name = categoryDocument.getName();
        this.subCategoryResponse = new SubCategoryResponse(categoryDocument.getSubCategory());
    }

    public Long getCategoryGroupId() {
        return categoryGroupId;
    }

    public String getName() {
        return name;
    }

    public SubCategoryResponse getSubCategoryResponse() {
        return subCategoryResponse;
    }

    @Override
    public String toString() {
        return """
                "categoryGroupId": %s,
                "name": %s,
                "subCategories": %s
                """.formatted(categoryGroupId, name, subCategoryResponse);
    }
}
