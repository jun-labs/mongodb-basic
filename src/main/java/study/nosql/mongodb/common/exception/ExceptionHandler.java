package study.nosql.mongodb.common.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.nosql.mongodb.business.domain.common.exception.BusinessException;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ErrorResponse catchBusinessException(BusinessException businessException) {
        return ErrorResponse.of(businessException);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse catchBadRequest(BusinessException businessException) {
        return ErrorResponse.of(businessException);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ErrorResponse catchUnresolvedException(BusinessException businessException) {
        return ErrorResponse.of(businessException);
    }
}
