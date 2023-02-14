package study.nosql.mongodb.business.web.post.presentation.dto.request;

public class PostUpdateRequest {

    private String content;

    private PostUpdateRequest() {
    }

    public PostUpdateRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("content: %s", content);
    }
}
