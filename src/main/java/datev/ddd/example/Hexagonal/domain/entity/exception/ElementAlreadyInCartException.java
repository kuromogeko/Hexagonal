package datev.ddd.example.Hexagonal.domain.entity.exception;

import com.github.cstettler.dddttc.stereotype.BusinessException;

@BusinessException
public class ElementAlreadyInCartException extends Exception {
    public ElementAlreadyInCartException(String message) {
        super(message);
    }
}
