package fr.fnicolas.bpm.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorDto extends ErrorDto {
    private String field;
    private String value;
}
