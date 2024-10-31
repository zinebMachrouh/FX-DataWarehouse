package org.example.fxdatawarehouse.Services.Impl;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.example.fxdatawarehouse.DTO.DealDTO;
import org.example.fxdatawarehouse.DTO.DealResponseDTO;
import org.example.fxdatawarehouse.Exceptions.AlreadyExistsException;
import org.example.fxdatawarehouse.Exceptions.CurrencyPatternException;
import org.example.fxdatawarehouse.Models.Deal;
import org.example.fxdatawarehouse.Repositories.DealRepository;
import org.example.fxdatawarehouse.Services.DealService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final ModelMapper modelMapper;

    @Override
    public DealResponseDTO saveDeal(DealDTO dealDTO) {
        if (!isValidCurrency(dealDTO.getFromCurrency())) {
            throw new CurrencyPatternException("Currency " + dealDTO.getFromCurrency() + " must be 3 characters");
        }
        if (!isValidCurrency(dealDTO.getToCurrency())) {
            throw new CurrencyPatternException("Currency " + dealDTO.getToCurrency() + " must be 3 characters");
        }
        if (!dealDTO.getId().isEmpty() && dealRepository.existsById(dealDTO.getId())) {
            throw new AlreadyExistsException("Deal with id " + dealDTO.getId() + " already exists");
        }
        Deal deal = modelMapper.map(dealDTO, Deal.class);
        deal.setDealTimestamp(LocalDateTime.now());
        deal = dealRepository.save(deal);
        return modelMapper.map(deal, DealResponseDTO.class);
    }

    @Override
    public List<DealResponseDTO> getAllDeals() {
        List<Deal> deals = dealRepository.findAll();
        return deals.stream()
                .map(deal -> modelMapper.map(deal, DealResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DealResponseDTO> saveBatch(List<DealDTO> dealDTOs) {
        ArrayList<DealResponseDTO> result = new ArrayList<>();
        dealDTOs.forEach(dealDTO -> {
            try {
                result.add(saveDeal(dealDTO));
            } catch (AlreadyExistsException e) {
                log.info("Deal with id " + dealDTO.getId() + " already exists");
            }catch (CurrencyPatternException e) {
                log.info("Currency " + dealDTO.getFromCurrency() + " is invalid");
            }
        });
        return result;
    }

    private boolean isValidCurrency(String currency) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3}$");
        return pattern.matcher(currency).matches();
    }
}
