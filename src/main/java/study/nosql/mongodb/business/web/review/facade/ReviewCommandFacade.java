package study.nosql.mongodb.business.web.review.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.nosql.mongodb.business.common.mongodb.MongoDBCommandService;
import study.nosql.mongodb.business.domain.member.entity.Member;
import study.nosql.mongodb.business.domain.post.entity.Post;
import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.business.web.common.lock.MySQLLockService;
import study.nosql.mongodb.business.web.member.application.MemberQueryService;
import study.nosql.mongodb.business.web.post.application.PostQueryService;
import study.nosql.mongodb.business.web.review.application.ReviewCommandService;
import study.nosql.mongodb.business.web.review.application.ReviewQueryService;

import study.nosql.mongodb.common.documents.review.document.ReviewDocument;

@Component
@RequiredArgsConstructor
public class ReviewCommandFacade {

    private static final String FIXED_KEY_PREFIX = "#{REVIEW}/{%s}";

    private final MySQLLockService mySQLLockFacade;
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;
    private final MemberQueryService memberQueryService;
    private final PostQueryService postQueryService;
    private final MongoDBCommandService<ReviewDocument, Review> mongoDBCommandService;

    @Transactional
    public Review writeReview(Long memberId,
                              Long postId,
                              String content) {
        Review review = null;
        try {
            mySQLLockFacade.lock(createKey(postId));
            Member findMember = memberQueryService.findMemberById(memberId);
            Post findPost = postQueryService.findPostById(postId);

            review = reviewCommandService.writeReview(
                    findMember.getMemberId(),
                    findPost.getPostId(),
                    content
            );
            mongoDBCommandService.save(review.getPostId(), new ReviewDocument(review));
        } catch (Exception e) {
            // 롤백
        } finally {
            mySQLLockFacade.releaseLock(createKey(postId));
        }
        return review;
    }

    @Transactional
    public void updateReview(Long memberId,
                             Long postId,
                             Long reviewId,
                             String content) {
        try {
            mySQLLockFacade.lock(createKey(postId));
            Member findMember = memberQueryService.findMemberById(memberId);
            Post findPost = postQueryService.findPostById(postId);

            Review updatedReview = reviewCommandService.updateReview(
                    findMember.getMemberId(),
                    postId,
                    reviewId,
                    content
            );
            mongoDBCommandService.update(postId, updatedReview);
        } catch (Exception e) {
            // 롤백
        } finally {
            mySQLLockFacade.releaseLock(createKey(postId));
        }
    }

    @Transactional
    public void deleteReview(Long memberId,
                             Long postId,
                             Long reviewId) {
        try {
            mySQLLockFacade.lock(createKey(postId));
            memberQueryService.findMemberById(memberId);
            postQueryService.findPostById(postId);
            Review findReview = reviewQueryService.findReviewById(reviewId);
            reviewCommandService.deleteReview(memberId, findReview);
            mongoDBCommandService.delete(postId, findReview);
        } catch (Exception e) {
            // 롤백
        } finally {
            mySQLLockFacade.releaseLock(createKey(postId));
        }
    }

    public static String createKey(Long aggregationId) {
        return String.format(FIXED_KEY_PREFIX, aggregationId);
    }
}
