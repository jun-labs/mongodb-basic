package study.nosql.mongodb.business.web.post.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.nosql.mongodb.business.domain.category.entity.ConcreteCategory;
import study.nosql.mongodb.business.domain.image.entity.PostImage;
import study.nosql.mongodb.business.domain.image.entity.ReviewImage;
import study.nosql.mongodb.business.domain.member.entity.Member;
import study.nosql.mongodb.business.domain.post.entity.Post;
import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.business.web.category.application.CategoryQueryService;
import study.nosql.mongodb.business.web.common.mapper.DocumentMapper;
import study.nosql.mongodb.business.web.post.application.mongodb.MongoDBPostCommandService;
import study.nosql.mongodb.business.web.post.application.mongodb.MongoDBPostQueryService;
import study.nosql.mongodb.business.web.image.application.PostImageQueryService;
import study.nosql.mongodb.business.web.image.application.ReviewImageQueryService;
import study.nosql.mongodb.business.web.member.application.MemberQueryService;
import study.nosql.mongodb.business.web.post.application.PostQueryService;
import study.nosql.mongodb.business.web.post.presentation.dto.response.PostResponse;
import study.nosql.mongodb.business.web.review.application.ReviewQueryService;
import study.nosql.mongodb.common.documents.post.document.PostDocument;

import java.util.List;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class PostQueryFacade {

    // Single
    private final PostQueryService postQueryService;
    private final MemberQueryService memberQueryService;
    private final CategoryQueryService categoryQueryService;

    // Collections
    private final ReviewQueryService reviewQueryService;
    private final PostImageQueryService postImageQueryService;
    private final ReviewImageQueryService reviewImageQueryService;

    // MongoDB
    private final MongoDBPostQueryService mongoDBPostQueryService;
    private final MongoDBPostCommandService mongoDBPostCommandService;
    private final DocumentMapper documentMapper;

    public PostResponse findPostById(Long postId) {
        PostDocument document = mongoDBPostQueryService.findPostDocumentByPostId(postId);

        if (nonNull(document)) {
            return PostResponse.of(document);
        }

        upsertPost(postId);

        PostDocument savedPostDocument = mongoDBPostQueryService.findPostDocumentByPostId(postId);
        return PostResponse.of(savedPostDocument);
    }

    @Transactional
    public void upsertPost(Long postId) {
        Post findPost = postQueryService.findPostById(postId);
        Member findWriter = memberQueryService.findMemberById(findPost.getMemberId());
        ConcreteCategory findCategory = categoryQueryService.findConcreteCategoryByPostId(
                findPost.getConcreteCategoryId()
        );
        List<Review> findReviews = reviewQueryService.findReviewsByPostId(postId);
        List<PostImage> findPostImages = postImageQueryService.findReviewImagesByPostId(postId);
        List<ReviewImage> findReviewImages = reviewImageQueryService.findReviewImagesByPostId(postId);

        PostDocument postDocument = PostDocument.from(
                findPost,
                documentMapper.convertMemberToDocument(findWriter),
                documentMapper.convertCategoryToDocument(findCategory),
                documentMapper.convertReviewsToDocument(findReviews, findReviewImages),
                documentMapper.convertPostImageToDocument(findPostImages)
        );

        mongoDBPostCommandService.save(postId, postDocument);
    }
}
