package ait.cochort49.shop_g49_1.exceprionHandling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This is SecondTestException")
public class SecondTestException extends RuntimeException {
    public SecondTestException(String message) {
        super(message);
    }
}
