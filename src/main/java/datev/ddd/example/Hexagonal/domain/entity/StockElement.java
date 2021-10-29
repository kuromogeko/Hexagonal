package datev.ddd.example.Hexagonal.domain.entity;

public interface StockElement {
    Quantity getMaxQuantity();

    Money getSingleUnitPrice();
}
