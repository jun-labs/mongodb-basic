package study.nosql.mongodb.test.integration.post.query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.nosql.mongodb.business.web.post.facade.PostQueryFacade;
import study.nosql.mongodb.business.web.post.presentation.dto.response.PostResponse;
import study.nosql.mongodb.configuration.database.IntegrationTestBase;
import study.nosql.mongodb.configuration.annotation.InsertData;

@InsertData
@DisplayName("게시글 상세조회 통합 테스트")
class PostFindByIdIntegrationTest extends IntegrationTestBase {

    @Autowired
    private PostQueryFacade postQueryFacade;

    @Test
    @DisplayName("게시글이 존재할때 아이디로 조회하면 값이 반환된다.")
    void 게시글_상세조회_통합_테스트() {
        PostResponse response = postQueryFacade.findPostById(1L);

        Assertions.assertNotNull(response.getPostId());
    }
}
