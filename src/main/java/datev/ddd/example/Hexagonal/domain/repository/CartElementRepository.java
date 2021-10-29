package datev.ddd.example.Hexagonal.domain.repository;

import com.github.cstettler.dddttc.stereotype.Repository;
import datev.ddd.example.Hexagonal.domain.entity.CartElement;
import datev.ddd.example.Hexagonal.domain.entity.exception.CartElementNotFoundException;

import java.util.List;

@Repository
public interface CartElementRepository {
    CartElement findByName(String name) throws CartElementNotFoundException;

    List<CartElement> findAll();
}
