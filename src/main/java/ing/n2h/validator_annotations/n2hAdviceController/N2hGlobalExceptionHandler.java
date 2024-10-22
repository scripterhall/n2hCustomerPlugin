package ing.n2h.validator_annotations.n2hAdviceController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ing.n2h.validator_annotations.n2hDTO.N2hApiError;

@ControllerAdvice
public class N2hGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<N2hApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        N2hApiError apiError = new N2hApiError("Validation Failed", errors);
        return ResponseEntity.badRequest().body(apiError);
    }

}
