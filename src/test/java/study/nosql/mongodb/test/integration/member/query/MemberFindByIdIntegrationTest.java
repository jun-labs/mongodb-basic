package study.nosql.mongodb.test.integration.member.query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.nosql.mongodb.business.domain.common.exception.BusinessException;
import study.nosql.mongodb.business.domain.member.entity.Member;
import study.nosql.mongodb.business.web.member.application.MemberQueryService;
import study.nosql.mongodb.configuration.annotation.InsertData;
import study.nosql.mongodb.configuration.database.IntegrationTestBase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@InsertData
@DisplayName("회원 상세조회 통합 테스트")
class MemberFindByIdIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MemberQueryService memberQueryService;

    @Test
    @DisplayName("존재하지 않는 회원을 PK로 조회하면 BusinessException이 발생한다.")
    void 회원_상세조회_실패_통합_테스트() {
        Long inValidId = Long.MAX_VALUE;

        assertThatThrownBy(() -> memberQueryService.findMemberById(inValidId))
                .isInstanceOf(BusinessException.class)
                .hasMessage("회원을 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("존재하는 회원을 PK로 조회하면 상세정보를 알 수 있다.")
    void 회원_상세조회_성공_통합_테스트() {
        Long validId = 1L;
        Member findMember = memberQueryService.findMemberById(1L);

        Assertions.assertEquals(validId, findMember.getMemberId());
    }
}
