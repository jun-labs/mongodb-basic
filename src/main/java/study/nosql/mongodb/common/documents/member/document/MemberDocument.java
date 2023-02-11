package study.nosql.mongodb.common.documents.member.document;

import org.springframework.data.mongodb.core.mapping.Document;
import study.nosql.mongodb.business.domain.member.entity.Member;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Objects;

@Document(collation = "member")
public class MemberDocument {

    @Id
    private Long _id;

    @Column
    private Long memberId;

    @Column
    private String name;

    /**
     * @Nullary-Constructor. MongoDB 기본 생성자로 member document 패키지 외부에서 호출하지 말 것.
     */
    private MemberDocument() {
    }

    public MemberDocument(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
    }

    public Long get_id() {
        return _id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberDocument that)) return false;
        return getMemberId().equals(that.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId());
    }

    @Override
    public String toString() {
        return memberId.toString();
    }
}
