package baksakcci.shoppingmall.common;

import baksakcci.shoppingmall.common.response.Response;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Response> exception(NoSuchElementException e) {
        return ResponseEntity.status(404)
                .body(Response.error(404, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> exception(MethodArgumentNotValidException e) {
        return ResponseEntity.status(400)
                .body(Response.error(400, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Response> exception(IllegalStateException e) {
        return ResponseEntity.badRequest()
                .body(Response.error(400, e.getMessage()));
    }
}
