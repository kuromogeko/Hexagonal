package datev.ddd.example.Hexagonal.infrastructure.driving.boundary.dto;


import datev.ddd.example.Hexagonal.domain.entity.CartElement;
import datev.ddd.example.Hexagonal.domain.entity.Money;
import datev.ddd.example.Hexagonal.domain.entity.Quantity;
import lombok.Getter;

@Getter
public class StockElementDto {
    private final String name;
    private final Quantity maxQuantity;
    private final Money singleUnitPrice;

    public StockElementDto(CartElement cartElement) {
        this.maxQuantity = cartElement.getMaxQuantity();
        this.singleUnitPrice = cartElement.getSingleUnitPrice();
        this.name = cartElement.getName();
    }
}
