package study.nosql.mongodb.common.documents.image.document;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import study.nosql.mongodb.business.domain.image.entity.PostImage;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Objects;

@Document(collation = "post_image")
public class PostImageDocument {

    @Id
    private String _id;

    @Column
    private Long postImageId;

    @Column
    private Long postId;

    @Column
    private String imageUrl;

    /**
     * @Nullary-Constructor. MongoDB 기본 생성자로 image document 패키지 외부에서 호출하지 말 것.
     */
    private PostImageDocument() {
    }

    public PostImageDocument(PostImage postImage) {
        this._id = new ObjectId().toHexString();
        this.postImageId = postImage.getPostImageId();
        this.postId = postImage.getPostId();
        this.imageUrl = postImage.getImageUrl();
    }

    public String get_id() {
        return _id;
    }

    public Long getPostImageId() {
        return postImageId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostImageDocument that)) return false;
        return getPostImageId().equals(that.getPostImageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostImageId());
    }

    @Override
    public String toString() {
        return imageUrl;
    }
}
