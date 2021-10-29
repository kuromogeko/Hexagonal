package datev.ddd.example.Hexagonal.infrastructure.driving.boundary;

import datev.ddd.example.Hexagonal.domain.ShoppingCartManagementUseCase;
import datev.ddd.example.Hexagonal.domain.ShoppingCartPaymentUseCase;
import datev.ddd.example.Hexagonal.domain.StockListUseCase;
import datev.ddd.example.Hexagonal.domain.entity.Quantity;
import datev.ddd.example.Hexagonal.domain.entity.exception.CartElementNotFoundException;
import datev.ddd.example.Hexagonal.domain.entity.exception.CartNotFoundException;
import datev.ddd.example.Hexagonal.domain.entity.exception.ElementAlreadyInCartException;
import datev.ddd.example.Hexagonal.domain.entity.exception.InvalidQuantityException;
import datev.ddd.example.Hexagonal.domain.entity.exception.MaximumAmountReachedException;
import datev.ddd.example.Hexagonal.infrastructure.driving.boundary.dto.CreateInvoiceDto;
import datev.ddd.example.Hexagonal.infrastructure.driving.boundary.dto.ExceptionDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class ShoppingController {
    private final StockListUseCase stockList;
    private final ShoppingCartManagementUseCase shoppingCartManagement;
    private final ShoppingCartPaymentUseCase shoppingCartPayment;

    @GetMapping(path = "/carts/{id}")
    public ResponseEntity<?> getShoppingCartById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return ResponseEntity.ok(shoppingCartManagement.getCart(uuid));
        } catch (CartNotFoundException e) {
            return ResponseEntity.badRequest().body(new ExceptionDto(e));
        }
    }

    @PostMapping(path = "/carts")
    public ResponseEntity<?> createCart() {
        return ResponseEntity.ok(this.shoppingCartManagement.createShoppingCart());
    }

    @DeleteMapping(path = "/carts/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable UUID id) {
        try {
            this.shoppingCartManagement.deleteShoppingCart(id);
            return ResponseEntity.ok().build();
        } catch (CartNotFoundException e) {
            return ResponseEntity.badRequest().body(new ExceptionDto(e));
        }
    }

    @GetMapping(path = "/elements")
    public ResponseEntity<?> listPossibleCartElements() {
        return ResponseEntity.ok(stockList.listStockElements());
    }

    @PostMapping(path = "/invoice")
    public ResponseEntity<?> createInvoiceForCart(@RequestBody CreateInvoiceDto createInvoiceDto) {
        try {
            UUID id = UUID.fromString(createInvoiceDto.getId());
            shoppingCartPayment.createInvoice(id);
            return ResponseEntity.accepted().body(createInvoiceDto);
        } catch (CartNotFoundException e) {
            return ResponseEntity.badRequest().body(new ExceptionDto(e));
        }
    }

    @GetMapping(path = "/carts/{id}/price")
    public ResponseEntity<?> getPriceOfCart(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return ResponseEntity.ok(shoppingCartPayment.calculatePriceOfCart(uuid));
        } catch (CartNotFoundException e) {
            return ResponseEntity.badRequest().body(new ExceptionDto(e));
        }
    }

    // We can see that immediately going to the Domain layer is allowed too!
    @PutMapping(path = "/carts/{id}/elements/{elem}")
    public ResponseEntity<?> addElementToCart(@PathVariable UUID id, @PathVariable String elem, @RequestBody Quantity quantity) {
        try {
            return ResponseEntity.ok(this.shoppingCartManagement.addElementToShoppingCart(id, elem, quantity));
        } catch (CartNotFoundException | CartElementNotFoundException | MaximumAmountReachedException | ElementAlreadyInCartException | InvalidQuantityException e) {
            return ResponseEntity.badRequest().body(new ExceptionDto(e));
        }
    }

    @PatchMapping(path = "/carts/{id}/elements/{elem}")
    public ResponseEntity<?> changeQuantityOfElementInCart(@PathVariable UUID id, @PathVariable String elem, @RequestBody Quantity quantity) {
        try {
            return ResponseEntity.ok(this.shoppingCartManagement.changeQuantityOfElementInShoppingCart(id, elem, quantity));
        } catch (CartNotFoundException | CartElementNotFoundException | MaximumAmountReachedException | InvalidQuantityException e) {
            return ResponseEntity.badRequest().body(new ExceptionDto(e));
        }
    }

    @DeleteMapping(path = "/carts/{id}/elements/{elem}")
    public ResponseEntity<?> removeElementFromCart(@PathVariable UUID id, @PathVariable String elem) {
        try {
            return ResponseEntity.ok(this.shoppingCartManagement.deleteElementFromShoppingCart(id, elem));
        } catch (CartNotFoundException | CartElementNotFoundException e) {
            return ResponseEntity.badRequest().body(new ExceptionDto(e));
        }
    }

}
