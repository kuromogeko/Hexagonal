package datev.ddd.example.Hexagonal.infrastructure.driven.repository.mongodb;

import datev.ddd.example.Hexagonal.domain.entity.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

//Fun Fact: Spring gets confused when naming this interface MongoShoppingCartRepository and won't start
public interface SpringMongoShoppingCartRepository extends MongoRepository<ShoppingCart, UUID> {
}
