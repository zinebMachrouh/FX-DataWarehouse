package org.example.fxdatawarehouse.Services;

import org.example.fxdatawarehouse.DTO.DealDTO;

import java.util.List;

public interface DealService {
    public DealDTO saveDeal(DealDTO dealDTO);
    public List<DealDTO> getAllDeals();
}
