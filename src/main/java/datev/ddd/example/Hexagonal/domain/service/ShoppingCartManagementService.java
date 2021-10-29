package datev.ddd.example.Hexagonal.domain.service;

import com.github.cstettler.dddttc.stereotype.DomainService;
import datev.ddd.example.Hexagonal.domain.ShoppingCartManagementUseCase;
import datev.ddd.example.Hexagonal.domain.entity.Quantity;
import datev.ddd.example.Hexagonal.domain.entity.ShoppingCart;
import datev.ddd.example.Hexagonal.domain.entity.exception.CartElementNotFoundException;
import datev.ddd.example.Hexagonal.domain.entity.exception.CartNotFoundException;
import datev.ddd.example.Hexagonal.domain.entity.exception.ElementAlreadyInCartException;
import datev.ddd.example.Hexagonal.domain.entity.exception.InvalidQuantityException;
import datev.ddd.example.Hexagonal.domain.entity.exception.MaximumAmountReachedException;
import datev.ddd.example.Hexagonal.domain.repository.CartElementRepository;
import datev.ddd.example.Hexagonal.domain.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@DomainService
@AllArgsConstructor
public class ShoppingCartManagementService implements ShoppingCartManagementUseCase {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartElementRepository cartElementRepository;

    // USE CASE 0
    public ShoppingCart createShoppingCart() {
        return shoppingCartRepository.save(ShoppingCart.createDefaultShoppingCart());
    }

    // UC 0
    public void deleteShoppingCart(UUID id) throws CartNotFoundException {
        shoppingCartRepository.delete(id);
    }

    // USE CASE 0.1
    public ShoppingCart addElementToShoppingCart(UUID shoppingCartId, String cartElementName, Quantity quantity) throws CartNotFoundException, CartElementNotFoundException, MaximumAmountReachedException, ElementAlreadyInCartException, InvalidQuantityException {
        quantity.validate();
        var cart = shoppingCartRepository.findById(shoppingCartId);
        var item = cartElementRepository.findByName(cartElementName);
        cart.addToWarenkorb(item, quantity);
        return shoppingCartRepository.save(cart);
    }

    //UC 0.1
    public ShoppingCart changeQuantityOfElementInShoppingCart(UUID shoppingCartId, String cartElementName, Quantity quantity) throws CartNotFoundException, CartElementNotFoundException, MaximumAmountReachedException, InvalidQuantityException {
        quantity.validate();
        var cart = shoppingCartRepository.findById(shoppingCartId);
        cart.findElementAndSetQuantity(cartElementName, quantity);
        return shoppingCartRepository.save(cart);
    }

    // UC 0.1
    public ShoppingCart deleteElementFromShoppingCart(UUID shoppingCartId, String cartElementName) throws CartNotFoundException, CartElementNotFoundException {
        var cart = shoppingCartRepository.findById(shoppingCartId);
        cart.findElementAndRemove(cartElementName);
        return shoppingCartRepository.save(cart);
    }

    // USE CASE 0.2
    public ShoppingCart getCart(UUID id) throws CartNotFoundException {
        return shoppingCartRepository.findById(id);
    }


}
