package study.nosql.mongodb.business.domain.member.entity;

import study.nosql.mongodb.common.mapping.Deleted;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Member {

    @Id
    private Long memberId;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private Deleted deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 패키지 외부에서 호출하지 말 것.
     */
    protected Member() {
    }

    public Member(String name) {
        validate(name);
        this.name = name;
        this.deleted = Deleted.FALSE;
    }

    private void validate(String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("회원 이름을 입력해주세요.");
        }
        if (name.length() > 10) {
            throw new IllegalArgumentException("입력 가능한 회원 최대 이름 길이를 초과했습니다.");
        }
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public Deleted getDeleted() {
        return deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return getMemberId().equals(member.getMemberId());
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
