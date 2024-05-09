package fr.fnicolas.bpm.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto<ErrorDto>> handleException(EntityNotFoundException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorType("ENTITY_NOT_FOUND");
        errorDto.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(new ErrorResponseDto<>(errorDto), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto<ValidationErrorDto>> handleException(MethodArgumentNotValidException e) {
        final List<ValidationErrorDto> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(this::getValidationErrorDto)
                .toList();
        return new ResponseEntity<>(new ErrorResponseDto<>(errors), HttpStatus.BAD_REQUEST);
    }

    private ValidationErrorDto getValidationErrorDto(final FieldError error) {
        ValidationErrorDto errorDto = new ValidationErrorDto();
        errorDto.setErrorType("VALIDATION_ERROR");
        errorDto.setErrorMessage(error.getDefaultMessage());
        this.addFieldsInfo(error, errorDto);
        return errorDto;
    }

    private void addFieldsInfo(final FieldError fieldError,
                               final ValidationErrorDto errorDto) {
        errorDto.setErrorMessage(fieldError.getDefaultMessage());
        if (this.isFieldError(fieldError)) {
            errorDto.setField(fieldError.getField());
            errorDto.setValue(fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : null);
        }
    }

    private boolean isFieldError(FieldError fieldError) {
        return fieldError.getCode() != null && !fieldError.getCode().startsWith("Assert");
    }


}
