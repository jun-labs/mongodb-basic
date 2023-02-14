package study.nosql.mongodb.business.web.review.presentation.dto.request;

public class ReviewWriteRequest {

    private String content;
    private Long memberId;

    private ReviewWriteRequest() {
    }

    public ReviewWriteRequest(String content, Long memberId) {
        this.content = content;
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public Long getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return content;
    }
}
