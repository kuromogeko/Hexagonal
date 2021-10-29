package datev.ddd.example.Hexagonal.domain.service;

import com.github.cstettler.dddttc.stereotype.DomainService;
import datev.ddd.example.Hexagonal.domain.StockListUseCase;
import datev.ddd.example.Hexagonal.domain.repository.CartElementRepository;
import datev.ddd.example.Hexagonal.infrastructure.driving.boundary.dto.StockElementDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@DomainService
@Service
@AllArgsConstructor
public class StockListService implements StockListUseCase {
    private final CartElementRepository cartElementRepository;

    // USE CASE 2
    public List<StockElementDto> listStockElements() {
        return cartElementRepository.findAll().stream().map(StockElementDto::new).collect(Collectors.toList());
    }


}
