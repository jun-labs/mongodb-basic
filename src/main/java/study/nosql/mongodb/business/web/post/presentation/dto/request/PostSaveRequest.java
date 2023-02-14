package study.nosql.mongodb.business.web.post.presentation.dto.request;

import study.nosql.mongodb.common.documents.post.document.PostDocument;

public class PostSaveRequest {

    private Long memberId;
    private String content;

    private PostSaveRequest() {
    }

    public PostSaveRequest(Long memberId, String content) {
        this.memberId = memberId;
        this.content = content;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getContent() {
        return content;
    }

    public PostDocument toDocument() {
        return new PostDocument(memberId, content);
    }

    @Override
    public String toString() {
        return String.format("memberId: %s, content: %s", memberId, content);
    }
}
