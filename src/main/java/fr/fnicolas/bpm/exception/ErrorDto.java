package fr.fnicolas.bpm.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {
    private String errorType;
    private String errorMessage;
}
