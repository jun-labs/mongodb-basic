package study.nosql.mongodb.test.integration.review.query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.business.web.review.application.ReviewQueryService;
import study.nosql.mongodb.configuration.annotation.InsertData;
import study.nosql.mongodb.configuration.database.IntegrationTestBase;

import java.util.List;

@InsertData
@DisplayName("리뷰 조회 통합 테스트")
class ReviewsFindIntegrationTest extends IntegrationTestBase {

    @Autowired
    private ReviewQueryService reviewQueryService;

    @Test
    @DisplayName("게시글의 PK로 연관된 댓글을 조회할 수 있다.")
    void 게시글_PK로_연관된_댓글_조회_통합_테스트() {
        List<Review> reviews = reviewQueryService.findReviewsByPostId(1L);

        Assertions.assertTrue(!reviews.isEmpty());
    }
}
