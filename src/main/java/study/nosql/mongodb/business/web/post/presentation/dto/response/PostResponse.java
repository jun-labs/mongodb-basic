package study.nosql.mongodb.business.web.post.presentation.dto.response;

import study.nosql.mongodb.business.web.member.presentation.dto.response.MemberResponse;
import study.nosql.mongodb.business.web.review.presentation.dto.response.ReviewResponse;
import study.nosql.mongodb.common.documents.image.document.PostImageDocument;
import study.nosql.mongodb.common.documents.post.document.PostDocument;
import study.nosql.mongodb.common.documents.review.document.ReviewDocument;
import study.nosql.mongodb.business.domain.common.field.Deleted;
import study.nosql.mongodb.business.web.category.presentation.dto.response.CategoryResponse;
import study.nosql.mongodb.business.web.image.presentation.dto.response.PostImageResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PostResponse {

    private final Long postId;
    private final String content;
    private final int commentCount;
    private final Deleted deleted;
    private final MemberResponse member;
    private final CategoryResponse category;
    private final List<PostImageResponse> postImages;
    private final List<ReviewResponse> reviews;

    private PostResponse(PostDocument postDocument) {
        this.postId = postDocument.getPostId();
        this.content = postDocument.getContent();
        this.commentCount = postDocument.getComentCount();
        this.deleted = postDocument.getDeleted();
        this.member = new MemberResponse(postDocument.getMemberDocument());
        this.category = new CategoryResponse(postDocument.getCategoryDocument());
        this.postImages = convertToPostImageResponse(postDocument.getPostImagesDocument());
        this.reviews = convertToReviewResponse(postDocument.getReviewsDocument());
    }

    private List<PostImageResponse> convertToPostImageResponse(List<PostImageDocument> postImagesDocument) {
        return postImagesDocument.stream()
                .map(PostImageResponse::new)
                .collect(Collectors.toList());
    }

    private List<ReviewResponse> convertToReviewResponse(List<ReviewDocument> reviews) {
        return reviews.stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }

    public static PostResponse of(PostDocument postDocument) {
        return new PostResponse(postDocument);
    }

    public Long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public Deleted getDeleted() {
        return deleted;
    }

    public MemberResponse getMember() {
        return member;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public List<PostImageResponse> getPostImages() {
        return postImages;
    }

    public List<ReviewResponse> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return String.format("postId: %s", postId);
    }
}
