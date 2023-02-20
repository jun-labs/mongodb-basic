package study.nosql.mongodb.test.integration.review.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.nosql.mongodb.business.web.post.application.mongodb.MongoDBPostQueryService;
import study.nosql.mongodb.business.web.post.presentation.PostQueryController;
import study.nosql.mongodb.business.web.review.facade.ReviewCommandFacade;
import study.nosql.mongodb.common.documents.post.document.PostDocument;
import study.nosql.mongodb.common.documents.review.document.ReviewDocument;
import study.nosql.mongodb.configuration.annotation.Description;
import study.nosql.mongodb.configuration.annotation.InsertData;
import study.nosql.mongodb.configuration.database.IntegrationTestBase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@InsertData
@DisplayName("댓글 수정 통합 테스트")
class ReviewUpdateIntegrationTest extends IntegrationTestBase {

    @Autowired
    private ReviewCommandFacade reviewCommandFacade;

    @Autowired
    private PostQueryController postQueryController;

    @Autowired
    private MongoDBPostQueryService mongoDBPostQueryService;

    @Test
    @DisplayName("댓글을 수정하면 MongoDB에도 반영된다.")
    void 댓글_수정_통합_테스트() {
        // given
        initData();
        String expectedContent = "좋은 내용이네요.";

        // when
        reviewCommandFacade.updateReview(1L, 1L, 1L, "좋은 내용이네요.");
        PostDocument postDocument = mongoDBPostQueryService.findPostDocumentByPostId(1L);
        ReviewDocument reviewDocument = postDocument.findReviewDocumentById(1L);

        // then
        Assertions.assertEquals(expectedContent, reviewDocument.getContent());
    }

    @Test
    @DisplayName("100명의 사용자가 동시에 댓글을 수정해도 MongoDB에 반영된다.")
    void 댓글_수정_동시성_테스트() throws InterruptedException {
        // given
        initData();
        String expectedContent = "WOW";
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        // when
        for (int index = 1; index <= 100; index++) {
            final long reviewId = index;
            executorService.submit(() -> {
                try {
                    reviewCommandFacade.updateReview(1L, 1L, reviewId, "WOW");
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        // then
        PostDocument postDocument = mongoDBPostQueryService.findPostDocumentByPostId(1L);
        int reviewCount = postDocument.reviewSize();

        for (int index = 1; index < reviewCount - 1; index++) {
            ReviewDocument findReviewDocument = postDocument.findReviewDocumentById((long) index);
            Assertions.assertEquals(expectedContent, findReviewDocument.getContent());
        }
    }

    @Description(content = "컨트롤러에서 조회하는 순간 SQL 스크립트 실행으로 들어간 모든 데이터가 저장되기 때문에 컨트롤러를 활용한 초기화 진행")
    private void initData() {
        for (int index = 1; index <= 100; index++) {
            reviewCommandFacade.writeReview(1L, 1L, "Hello");
        }
        postQueryController.findPostById(1L);
    }
}
