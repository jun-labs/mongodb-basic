package study.nosql.mongodb.test.integration.review.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.business.web.post.application.mongodb.MongoDBPostQueryService;
import study.nosql.mongodb.business.web.post.presentation.PostQueryController;
import study.nosql.mongodb.business.web.review.facade.ReviewCommandFacade;
import study.nosql.mongodb.common.documents.post.document.PostDocument;
import study.nosql.mongodb.common.documents.review.document.ReviewDocument;
import study.nosql.mongodb.configuration.database.IntegrationTestBase;
import study.nosql.mongodb.configuration.annotation.InsertData;
import study.nosql.mongodb.configuration.annotation.Description;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@InsertData
@DisplayName("리뷰 작성 통합 테스트")
class ReviewWriteIntegrationTest extends IntegrationTestBase {

    @Autowired
    private ReviewCommandFacade reviewCommandFacade;

    @Autowired
    private PostQueryController postQueryController;

    @Autowired
    private MongoDBPostQueryService mongoDBPostQueryService;

    @Test
    @DisplayName("리뷰를 저장하면 MongoDB에도 함께 저장된다.")
    void 댓글_저장_통합_테스트() {
        // given
        initData();

        // when
        Review newReview = reviewCommandFacade.writeReview(1L, 1L, "좋은 커리큘럼이네요.");
        PostDocument postDocument = mongoDBPostQueryService.findPostDocumentByPostId(1L);
        ReviewDocument reviewDocument = postDocument.findReviewDocumentById(newReview.getReviewId());

        // then
        Assertions.assertNotNull(newReview.getReviewId());
        Assertions.assertNotNull(reviewDocument.getReviewId());
    }

    @Test
    @DisplayName("500명의 사용자가 동시에 댓글을 작성하면 게시글의 댓글은 500개가 된다.")
    void 댓글_저장_동시성_테스트() throws InterruptedException {
        // given
        initData();
        ExecutorService executorService = Executors.newFixedThreadPool(512);
        CountDownLatch countDownLatch = new CountDownLatch(500);

        // when
        for (int index = 1; index <= 500; index++) {
            executorService.submit(() -> {
                try {
                    reviewCommandFacade.writeReview(1L, 1L, "좋은 커리큘럼이네요.");
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        PostDocument postDocument = mongoDBPostQueryService.findPostDocumentByPostId(1L);

        Assertions.assertEquals(502, postDocument.getReviewsDocument().size());
    }

    @Description(content = "컨트롤러에서 조회하는 순간 SQL 스크립트 실행으로 들어간 모든 데이터가 저장되기 때문에 컨트롤러를 활용한 초기화 진행")
    private void initData() {
        postQueryController.findPostById(1L);
    }
}
