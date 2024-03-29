package study.nosql.mongodb.test.integration.category.query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.nosql.mongodb.business.domain.category.entity.CategoryGroup;
import study.nosql.mongodb.business.domain.category.entity.ConcreteCategory;
import study.nosql.mongodb.business.domain.category.entity.SubCategory;
import study.nosql.mongodb.business.domain.common.exception.BusinessException;
import study.nosql.mongodb.business.web.category.application.CategoryQueryService;
import study.nosql.mongodb.configuration.annotation.InsertData;
import study.nosql.mongodb.configuration.test.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@InsertData
@DisplayName("카테고리 조회 테스트")
class CategoryFindByIdIntegrationTest extends IntegrationTestBase {

    @Autowired
    private CategoryQueryService categoryQueryService;

    @Test
    @DisplayName("상세 카테고리 조회를 통해 상위 카테고리를 조회할 수 있다.")
    void 게시글_카테고리_조회_통합_테스트() {
        String expectedConcreteCategory = "컴퓨터";
        String expectedSubCategory = "자유게시판";
        String expectedCategoryGroup = "게시글";

        ConcreteCategory concreteCategory = categoryQueryService.findConcreteCategoryByPostId(1L);
        SubCategory subCategory = concreteCategory.getSubCategory();
        CategoryGroup categoryGroup = subCategory.getCategoryGroup();

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedCategoryGroup, categoryGroup.getName()),
                () -> Assertions.assertEquals(expectedSubCategory, subCategory.getName()),
                () -> Assertions.assertEquals(expectedConcreteCategory, concreteCategory.getName())
        );
    }

    @Test
    @DisplayName("존재하지 않는 카테고리를 조회하면 BusinessException이 발생한다.")
    void 게시글_카테고리_조회_실패_통합_테스트() {
        Long invalidCategoryId = Long.MAX_VALUE;
        Class<?> expectedClazz = BusinessException.class;
        String expectedMessage = "카테고리를 찾을 수 없습니다.";

        assertThatThrownBy(() -> categoryQueryService.findConcreteCategoryByPostId(invalidCategoryId))
                .isInstanceOf(expectedClazz)
                .hasMessage(expectedMessage);
    }
}
