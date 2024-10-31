package org.example.fxdatawarehouse.Services.Impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.fxdatawarehouse.DTO.DealDTO;
import org.example.fxdatawarehouse.Repositories.DealRepository;
import org.example.fxdatawarehouse.Services.DealService;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DealServiceImpl implements DealService {
    private DealRepository dealRepository;

    @Override
    public DealDTO saveDeal(DealDTO dealDTO) {
        return null;
    }

    @Override
    public List<DealDTO> getAllDeals() {
        return null;
    }
}
