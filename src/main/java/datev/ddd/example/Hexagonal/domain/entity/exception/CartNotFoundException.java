package datev.ddd.example.Hexagonal.domain.entity.exception;

import com.github.cstettler.dddttc.stereotype.BusinessException;

@BusinessException
public class CartNotFoundException extends Exception {
    public CartNotFoundException(String message) {
        super(message);
    }
}
