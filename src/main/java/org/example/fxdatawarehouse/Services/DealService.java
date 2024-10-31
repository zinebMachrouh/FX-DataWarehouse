package org.example.fxdatawarehouse.Services;

import org.example.fxdatawarehouse.DTO.DealDTO;
import org.example.fxdatawarehouse.DTO.DealResponseDTO;

import java.util.List;

public interface DealService {
    public DealResponseDTO saveDeal(DealDTO dealDTO);
    public List<DealResponseDTO> getAllDeals();
    public List<DealResponseDTO> saveBatch(List<DealDTO> dealDTOs);
}
