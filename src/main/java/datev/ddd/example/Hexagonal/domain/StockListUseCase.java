package datev.ddd.example.Hexagonal.domain;

import datev.ddd.example.Hexagonal.infrastructure.driving.boundary.dto.StockElementDto;

import java.util.List;

public interface StockListUseCase {
    List<StockElementDto> listStockElements();
}
