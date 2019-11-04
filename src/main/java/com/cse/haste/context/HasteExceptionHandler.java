package com.cse.haste.context;

import com.cse.haste.model.dto.Response;
import com.cse.haste.util.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author WangZhenqi
 */
@RestControllerAdvice
public class HasteExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(HasteExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Response<String> handleException(Exception e) {
        logger.error("AriesExceptionHandler: ", e);
        return new Response<>(StatusCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = HasteException.class)
    public Response<String> handleAriesException(HasteException e) {
        logger.error("AriesExceptionHandler: ", e);
        return new Response<>(e.getStatusCode());
    }
}
