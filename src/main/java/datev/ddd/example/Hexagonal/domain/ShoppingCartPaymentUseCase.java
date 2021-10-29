package datev.ddd.example.Hexagonal.domain;

import datev.ddd.example.Hexagonal.domain.entity.Money;
import datev.ddd.example.Hexagonal.domain.entity.exception.CartNotFoundException;

import java.util.UUID;

public interface ShoppingCartPaymentUseCase {
    Money calculatePriceOfCart(UUID shoppingCartId) throws CartNotFoundException;

    void createInvoice(UUID shoppingCartId) throws CartNotFoundException;
}
