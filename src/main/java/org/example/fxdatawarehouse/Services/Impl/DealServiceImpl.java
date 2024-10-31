package org.example.fxdatawarehouse.Services.Impl;

import lombok.AllArgsConstructor;

import org.example.fxdatawarehouse.DTO.DealDTO;
import org.example.fxdatawarehouse.DTO.DealResponseDTO;
import org.example.fxdatawarehouse.Exceptions.AlreadyExistsException;
import org.example.fxdatawarehouse.Exceptions.CurrencyPatternException;
import org.example.fxdatawarehouse.Models.Deal;
import org.example.fxdatawarehouse.Repositories.DealRepository;
import org.example.fxdatawarehouse.Services.DealService;
import org.example.fxdatawarehouse.Utils.CurrencyValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = Logger.getLogger(DealServiceImpl.class.getName());

    @Override
    public DealResponseDTO saveDeal(DealDTO dealDTO) {
        if (!CurrencyValidator.isValidCurrency(dealDTO.getFromCurrency())) {
            throw new CurrencyPatternException("Currency " + dealDTO.getFromCurrency() + " must be between 2 and 3 characters");
        }
        if (!CurrencyValidator.isValidCurrency(dealDTO.getToCurrency())) {
            throw new CurrencyPatternException("Currency " + dealDTO.getToCurrency() + " must be between 2 and 3 characters");
        }
        if (!dealDTO.getId().isEmpty() && dealRepository.existsById(dealDTO.getId())) {
            throw new AlreadyExistsException("Deal with id " + dealDTO.getId() + " already exists");
        }
        Deal deal = modelMapper.map(dealDTO, Deal.class);
        deal = dealRepository.save(deal);
        return modelMapper.map(deal, DealResponseDTO.class);
    }

    @Override
    public List<DealResponseDTO> getAllDeals() {
        return modelMapper.map(dealRepository.findAll(), List.class);
    }

    @Override
    public List<DealResponseDTO> saveBatch(List<DealDTO> dealDTOs) {
        ArrayList<DealResponseDTO> result = new ArrayList<>();
        dealDTOs.forEach(dealDTO -> {
            try {
                result.add(saveDeal(dealDTO));
            } catch (AlreadyExistsException e) {
                logger.info("Deal with id " + dealDTO.getId() + " already exists");
            }catch (CurrencyPatternException e) {
                logger.info("Currency " + dealDTO.getFromCurrency() + " is invalid");
            }
        });
        return result;
    }
}
