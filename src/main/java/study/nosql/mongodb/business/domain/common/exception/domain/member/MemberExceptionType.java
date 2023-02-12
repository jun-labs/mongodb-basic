package study.nosql.mongodb.business.domain.common.exception.domain.member;

import org.springframework.http.HttpStatus;
import study.nosql.mongodb.business.domain.common.exception.BaseExceptionType;

public enum MemberExceptionType implements BaseExceptionType {
    MEMBER_NOT_FOUND_EXCEPTION(404, "회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    MemberExceptionType(
            int code,
            String message,
            HttpStatus httpStatus
    ) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public int getErrorCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
