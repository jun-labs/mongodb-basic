package study.nosql.mongodb.business.web.review.application.mongodb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import study.nosql.mongodb.business.common.mongodb.MongoDBCommandService;
import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.business.web.post.application.mongodb.MongoDBPostQueryService;
import study.nosql.mongodb.common.documents.post.document.PostDocument;
import study.nosql.mongodb.common.documents.review.document.ReviewDocument;

@Service
@RequiredArgsConstructor
public class MongoDBReviewCommandService implements MongoDBCommandService<ReviewDocument, Review> {

    private final MongoDBPostQueryService mongoDBPostQueryService;
    private final MongoTemplate mongoTemplate;

    @Override
    public ReviewDocument save(Long aggregationId, ReviewDocument reviewDocument) {
        PostDocument findPostDocument = mongoDBPostQueryService.findPostDocumentByPostId(aggregationId);
        findPostDocument.addReview(reviewDocument);
        mongoTemplate.save(findPostDocument);
        return reviewDocument;
    }

    @Override
    public void update(Long aggregationId, Review review) {
        PostDocument findPostDocument = mongoDBPostQueryService.findPostDocumentByPostId(aggregationId);
        findPostDocument.updateReview(review);
        mongoTemplate.save(findPostDocument);
    }

    @Override
    public void delete(Long aggregationId, Review review) {
        PostDocument findPostDocument = mongoDBPostQueryService.findPostDocumentByPostId(aggregationId);
        findPostDocument.deleteReview(review.getReviewId());
        mongoTemplate.save(findPostDocument);
    }
}
