package com.sting.test.security;

import com.sting.core.project.SRS;
import com.sting.core.project.StException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = StException.class)
    public SRS miExceptionHandler(StException ex) {
        log.info(ex.getMessage());
        log.info(ExceptionUtils.getStackTrace(ex));
        return ex.getResponse();
    }

    @ExceptionHandler(value = Exception.class)
    public SRS exceptionHandler(Throwable ex) {
        log.info(ExceptionUtils.getStackTrace(ex));
        return SRS.byError();
    }

}
