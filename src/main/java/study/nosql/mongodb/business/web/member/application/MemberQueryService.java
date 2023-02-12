package study.nosql.mongodb.business.web.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.nosql.mongodb.business.domain.common.exception.BusinessException;
import study.nosql.mongodb.business.domain.member.entity.Member;
import study.nosql.mongodb.business.domain.member.infrastructure.command.MemberJpaRepository;

import static study.nosql.mongodb.business.domain.common.exception.domain.member.MemberExceptionType.MEMBER_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberJpaRepository memberJpaRepository;

    @Transactional(readOnly = true)
    public Member findMemberById(Long memberId) {
        return memberJpaRepository.findById(memberId)
                .orElseThrow(()-> BusinessException.of(MEMBER_NOT_FOUND_EXCEPTION));
    }
}
