package study.nosql.mongodb.business.web.member.presentation.dto.response;

import study.nosql.mongodb.common.documents.member.document.MemberDocument;

public class MemberResponse {

    private final Long memberId;
    private final String name;

    public MemberResponse(MemberDocument memberDocument) {
        this.memberId = memberDocument.getMemberId();
        this.name = memberDocument.getName();
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("memberId: %s", memberId);
    }
}
