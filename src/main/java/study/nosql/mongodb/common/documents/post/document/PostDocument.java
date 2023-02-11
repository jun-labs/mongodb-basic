package study.nosql.mongodb.common.documents.post.document;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;
import study.nosql.mongodb.business.domain.post.entity.Post;
import study.nosql.mongodb.business.domain.review.entity.Review;
import study.nosql.mongodb.common.documents.category.document.CategoryDocument;
import study.nosql.mongodb.common.documents.image.document.PostImageDocument;
import study.nosql.mongodb.common.documents.member.document.MemberDocument;
import study.nosql.mongodb.common.documents.review.document.ReviewDocument;
import study.nosql.mongodb.business.domain.common.Deleted;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 게시글 컬렉션 스키마는 카테고리, 글 작성자, 댓글로 제한한다.
 */
@Slf4j
@Document(collection = "post")
public class PostDocument {

    @Id
    private String _id;

    @Column
    private Long postId;

    @Column
    private String content;

    @Column
    private Integer comentCount;

    @Column
    private Deleted deleted;

    @Column
    private MemberDocument memberDocument;

    @Column
    private CategoryDocument categoryDocument;

    @Column
    private List<PostImageDocument> postImagesDocument;

    @Column
    private List<ReviewDocument> reviewsDocument;

    /**
     * @Nullary-Constructor. MongoDB 기본 생성자로 post document 패키지 외부에서 호출하지 말 것.
     */
    private PostDocument() {
    }

    private PostDocument(
            Post post,
            MemberDocument memberDocument,
            CategoryDocument categoryDocument,
            List<ReviewDocument> reviewsDocument,
            List<PostImageDocument> postImagesDocument
    ) {
        this._id = new ObjectId().toHexString();
        this.postId = post.getPostId();
        this.content = post.getContent();
        this.comentCount = post.getComentCount();
        this.memberDocument = memberDocument;
        this.deleted = post.getDeleted();
        this.categoryDocument = categoryDocument;
        this.reviewsDocument = reviewsDocument;
        this.postImagesDocument = postImagesDocument;
    }

    public PostDocument(
            Long memberId,
            String content
    ) {
        this.content = content;
        this.comentCount = 0;
        this.deleted = Deleted.FALSE;
    }

    public static PostDocument from(
            Post post,
            MemberDocument memberDocument,
            CategoryDocument categoryDocument,
            List<ReviewDocument> reviewsDocument,
            List<PostImageDocument> postImagesDocument
    ) {
        return new PostDocument(
                post,
                memberDocument,
                categoryDocument,
                reviewsDocument,
                postImagesDocument
        );
    }

    public String get_id() {
        return _id;
    }

    public Long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public Integer getComentCount() {
        return comentCount;
    }

    public MemberDocument getMemberDocument() {
        return memberDocument;
    }

    public CategoryDocument getCategoryDocument() {
        return categoryDocument;
    }

    public List<PostImageDocument> getPostImagesDocument() {
        return postImagesDocument;
    }

    public List<ReviewDocument> getReviewsDocument() {
        return reviewsDocument;
    }

    public int reviewSize() {
        return reviewsDocument.size();
    }

    public Deleted getDeleted() {
        return deleted;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public ReviewDocument findReviewDocumentById(Long reviewId) {
        return reviewsDocument.stream()
                .filter(eachReview -> eachReview.getReviewId().equals(reviewId))
                .findAny()
                .orElseThrow();
    }

    public void addReview(ReviewDocument reviewDocument) {
        this.reviewsDocument.add(reviewDocument);
    }

    public void updateReview(Review review) {
        ReviewDocument findReview = this.reviewsDocument.stream()
                .filter(isEqualTo(review.getReviewId()))
                .findAny()
                .orElseThrow();
        findReview.update(review);
    }

    public void deleteReview(Long targetId) {
        ReviewDocument findReview = this.reviewsDocument.stream()
                .filter(isEqualTo(targetId))
                .findAny()
                .orElseThrow();
        this.reviewsDocument.remove(findReview);
    }

    private static Predicate<ReviewDocument> isEqualTo(Long reviewId) {
        return review -> review.getReviewId().equals(reviewId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostDocument that)) return false;
        return getPostId().equals(that.getPostId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostId());
    }

    @SneakyThrows
    @Override
    public String toString() {
        String text = """
                {
                    "_id": %s,
                    "postId": %s,
                    "memberDocument": %s,
                    "content": %s,
                    "commentCount": %d,
                    "categoryDocument": %s
                }
                """.formatted(
                _id,
                postId,
                memberDocument,
                content,
                comentCount,
                categoryDocument
        );
        JSONObject json = new JSONObject(text);
        return json.toString(5);
    }
}
