package datev.ddd.example.Hexagonal.infrastructure.driving.boundary.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;


@Getter
public class CreateInvoiceDto {
    private final String id;

    @JsonCreator
    public CreateInvoiceDto(String id) {
        this.id = id;
    }
}
