package datev.ddd.example.Hexagonal.domain.publisher;

// Domain event publisher
public interface InvoiceEventPublisher {
    void createInvoice(CreateInvoiceEvent event);
}
