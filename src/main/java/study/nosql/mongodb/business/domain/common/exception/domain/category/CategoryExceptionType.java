package study.nosql.mongodb.business.domain.common.exception.domain.category;

import org.springframework.http.HttpStatus;
import study.nosql.mongodb.business.domain.common.exception.BaseExceptionType;

public enum CategoryExceptionType implements BaseExceptionType {
    CATEGORY_NOT_FOUND_EXCEPTION(404, "카테고리를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    CategoryExceptionType(
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
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
}
