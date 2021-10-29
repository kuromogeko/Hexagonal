package datev.ddd.example.Hexagonal.domain;

import datev.ddd.example.Hexagonal.domain.entity.Quantity;
import datev.ddd.example.Hexagonal.domain.entity.ShoppingCart;
import datev.ddd.example.Hexagonal.domain.entity.exception.CartElementNotFoundException;
import datev.ddd.example.Hexagonal.domain.entity.exception.CartNotFoundException;
import datev.ddd.example.Hexagonal.domain.entity.exception.ElementAlreadyInCartException;
import datev.ddd.example.Hexagonal.domain.entity.exception.InvalidQuantityException;
import datev.ddd.example.Hexagonal.domain.entity.exception.MaximumAmountReachedException;

import java.util.UUID;

public interface ShoppingCartManagementUseCase {
    //0
    ShoppingCart createShoppingCart();

    void deleteShoppingCart(UUID id) throws CartNotFoundException;

    //0.1
    ShoppingCart addElementToShoppingCart(UUID shoppingCartId, String cartElementName, Quantity quantity) throws CartNotFoundException, CartElementNotFoundException, MaximumAmountReachedException, ElementAlreadyInCartException, InvalidQuantityException;

    ShoppingCart changeQuantityOfElementInShoppingCart(UUID shoppingCartId, String cartElementName, Quantity quantity) throws CartNotFoundException, CartElementNotFoundException, MaximumAmountReachedException, InvalidQuantityException;

    ShoppingCart deleteElementFromShoppingCart(UUID shoppingCartId, String cartElementName) throws CartNotFoundException, CartElementNotFoundException;

    //0.2
    ShoppingCart getCart(UUID id) throws CartNotFoundException;

}
