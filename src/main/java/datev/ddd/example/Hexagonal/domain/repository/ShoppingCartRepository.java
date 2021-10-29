package datev.ddd.example.Hexagonal.domain.repository;

import datev.ddd.example.Hexagonal.domain.entity.ShoppingCart;
import datev.ddd.example.Hexagonal.domain.entity.exception.CartNotFoundException;

import java.util.UUID;


public interface ShoppingCartRepository {
    ShoppingCart findById(UUID id) throws CartNotFoundException;

    ShoppingCart save(ShoppingCart shoppingCart);

    void delete(UUID id) throws CartNotFoundException;
}
