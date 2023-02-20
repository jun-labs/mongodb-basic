package study.nosql.mongodb.test.integration.review.command;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.nosql.mongodb.business.web.post.application.mongodb.MongoDBPostQueryService;
import study.nosql.mongodb.business.web.post.presentation.PostQueryController;
import study.nosql.mongodb.business.web.review.facade.ReviewCommandFacade;
import study.nosql.mongodb.common.documents.post.document.PostDocument;
import study.nosql.mongodb.configuration.database.IntegrationTestBase;
import study.nosql.mongodb.configuration.annotation.InsertData;
import study.nosql.mongodb.configuration.annotation.Description;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@InsertData
@DisplayName("댓글 삭제 통합 테스트")
class ReviewDeleteIntegrationTest extends IntegrationTestBase {

    @Autowired
    private ReviewCommandFacade reviewCommandFacade;

    @Autowired
    private PostQueryController postQueryController;

    @Autowired
    private MongoDBPostQueryService mongoDBPostQueryService;

    @Test
    @DisplayName("댓글을 삭제하면 MongoDB에도 반영된다.")
    void 댓글_저장_통합_테스트() {
        // given
        initData();

        // when
        reviewCommandFacade.deleteReview(1L, 1L, 1L);
        PostDocument postDocument = mongoDBPostQueryService.findPostDocumentByPostId(1L);

        // then - 스크립트 한 개 + 반복문 10개
        Assertions.assertEquals(11, postDocument.reviewSize());
    }

    @Test
    @DisplayName("100명의 사용자가 동시에 댓글을 삭제해도 MongoDB에 반영된다.")
    void 댓글_삭제_동시성_테스트() throws InterruptedException {
        // given
        initData();
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        // when
        for (int index = 1; index <= 10; index++) {
            final long reviewId = index;
            executorService.submit(() -> {
                try {
                    reviewCommandFacade.deleteReview(1L, 1L, reviewId);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        PostDocument postDocument = mongoDBPostQueryService.findPostDocumentByPostId(1L);

        // then - 스크립트 한 개
        Assertions.assertEquals(2, postDocument.reviewSize());
    }

    @Description(content = "컨트롤러에서 조회하는 순간 SQL 스크립트 실행으로 들어간 모든 데이터가 저장되기 때문에 컨트롤러를 활용한 초기화 진행")
    private void initData() {
        // when
        for (int index = 1; index <= 10; index++) {
            reviewCommandFacade.writeReview(1L, 1L, "Hello");
        }
        postQueryController.findPostById(1L);
    }
}
