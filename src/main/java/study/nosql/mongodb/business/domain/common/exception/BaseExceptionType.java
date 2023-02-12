package study.nosql.mongodb.business.domain.common.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
    int getErrorCode();

    String getMessage();

    HttpStatus getHttpStatus();
}

