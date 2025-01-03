package ait.cochort49.shop_g49_1.exceprionHandling;


import ait.cochort49.shop_g49_1.exceprionHandling.exceptions.ThirdTestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ThirdTestException.class)
    public ResponseEntity<Response> handleThirdTestException(ThirdTestException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // BindingResult - Поля с ошибками. Получить можно у ex -> getBindingResult()
    // FieldError = это класс, который представляет ошибку, связанную с конкретным полем

    //    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(MethodArgumentNotValidException ex) {

        // Создаем StringBuilder для накопления сообщений об ошибках
        StringBuilder errorMessage = new StringBuilder();

        //Перебираю все ошибки валидации
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            // Добавляю сообщение об ошибке текущего поля
            errorMessage
                    .append(error.getField())
                    .append(" -> ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }

        // Создаем объект Response с накопленным сообщениям об ошибках
        Response response = new Response(errorMessage.toString());

        // Возвращаем ResponseEntity с объектом Response и статусом BAD_REQUEST
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseList> handleValidationExceptionList(MethodArgumentNotValidException ex) {

        // Создаем список для накопления сообщений об ошибках
        List<String> errors = new ArrayList<>();

        //Перебираю все ошибки валидации
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            // Добавляю сообщение об ошибке текущего поля
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        // Создаем объект ResponseList с накопленным сообщениям об ошибках
        ResponseList response = new ResponseList(errors);

        // Возвращаем ResponseEntity с объектом Response и статусом BAD_REQUEST
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProductValidationException.class)
    public ResponseEntity<String> handleProductValidationException(ProductValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<String> handleProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
