package datev.ddd.example.Hexagonal.domain.entity.exception;

import com.github.cstettler.dddttc.stereotype.BusinessException;

@BusinessException
public class CartElementNotFoundException extends Exception {
    public CartElementNotFoundException(String message) {
        super(message);
    }
}
