package datev.ddd.example.Hexagonal.domain.service;

import com.github.cstettler.dddttc.stereotype.DomainService;
import datev.ddd.example.Hexagonal.domain.ShoppingCartPaymentUseCase;
import datev.ddd.example.Hexagonal.domain.entity.Money;
import datev.ddd.example.Hexagonal.domain.entity.exception.CartNotFoundException;
import datev.ddd.example.Hexagonal.domain.publisher.CreateInvoiceEvent;
import datev.ddd.example.Hexagonal.domain.publisher.InvoiceEventPublisher;
import datev.ddd.example.Hexagonal.domain.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@DomainService
@AllArgsConstructor
public class ShoppingCartPaymentService implements ShoppingCartPaymentUseCase {
    private final ShoppingCartRepository shoppingCartRepository;
    private final InvoiceEventPublisher invoiceEventPublisher;

    //UC 1
    public Money calculatePriceOfCart(UUID shoppingCartId) throws CartNotFoundException {
        var cart = shoppingCartRepository.findById(shoppingCartId);
        return cart.calculatePrices();
    }

    //USE CASE 1.1
    public void createInvoice(UUID shoppingCartId) throws CartNotFoundException {
        var cart = shoppingCartRepository.findById(shoppingCartId);
        var event = new CreateInvoiceEvent(cart, LocalDate.now());
        invoiceEventPublisher.createInvoice(event);
    }

}
