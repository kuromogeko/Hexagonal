package datev.ddd.example.Hexagonal.infrastructure.driving.boundary.dto;

import lombok.Getter;

@Getter
public class ExceptionDto {
    private final String message;

    public ExceptionDto(Exception e) {
        this.message = e.getMessage();
    }
}
