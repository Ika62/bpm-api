package fr.fnicolas.bpm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponseDto<T extends ErrorDto>{

    private List<T> errors;

    public ErrorResponseDto(T error) {
        this.errors = List.of(error);
    }

}
