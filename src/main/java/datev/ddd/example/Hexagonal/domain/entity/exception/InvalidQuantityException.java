package datev.ddd.example.Hexagonal.domain.entity.exception;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException() {
        super("The given quantity is not a valid quantity");
    }
}
