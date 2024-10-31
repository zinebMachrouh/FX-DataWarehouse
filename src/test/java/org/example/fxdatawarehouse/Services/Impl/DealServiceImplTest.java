package org.example.fxdatawarehouse.Services.Impl;

import org.example.fxdatawarehouse.DTO.DealDTO;
import org.example.fxdatawarehouse.DTO.DealResponseDTO;
import org.example.fxdatawarehouse.Exceptions.AlreadyExistsException;
import org.example.fxdatawarehouse.Exceptions.CurrencyPatternException;
import org.example.fxdatawarehouse.Models.Deal;
import org.example.fxdatawarehouse.Repositories.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class DealServiceImplTest {
    @Mock
    private DealRepository dealRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DealServiceImpl dealService;

    private Deal deal1;
    private Deal deal2;
    private DealDTO dealDTO;
    private DealResponseDTO dealResponseDTO1;
    private DealResponseDTO dealResponseDTO2;

    @BeforeEach
    public void init(){
        dealDTO = DealDTO.builder()
                .id("deal-1")
                .fromCurrency("usd")
                .toCurrency("mad")
                .amount(150).build();

        deal1 = Deal.builder()
                .id("deal-1")
                .fromCurrency("USD")
                .toCurrency("MAD")
                .amount(100)
                .build();
        deal2 = Deal.builder()
                .id("deal-2")
                .fromCurrency("EUR")
                .toCurrency("USD")
                .amount(200)
                .build();

        dealResponseDTO1 = DealResponseDTO.builder()
                .id("deal-1")
                .fromCurrency("USD")
                .toCurrency("MAD")
                .amount(100)
                .build();
        dealResponseDTO2 = DealResponseDTO.builder()
                .id("deal-2")
                .fromCurrency("EUR")
                .toCurrency("USD")
                .amount(200)
                .build();
    }

    @Test
    @DisplayName("testing create method")
    void testCreate() {
        given(dealRepository.existsById("deal-1")).willReturn(false);
        given(modelMapper.map(dealDTO, Deal.class)).willReturn(deal1);
        given(dealRepository.save(deal1)).willReturn(deal1);
        given(modelMapper.map(deal1, DealResponseDTO.class)).willReturn(dealResponseDTO1);
        dealService.saveDeal(dealDTO);
        assertEquals(dealDTO.getId(), deal1.getId());
    }

    @Test
    @DisplayName("testing the invalidity of the currency")
    void testCurrencyValidation(){
        dealDTO.setFromCurrency("mad1");
        CurrencyPatternException exception1 = assertThrows(CurrencyPatternException.class, ()->{
            dealService.saveDeal(dealDTO);
        });
        dealDTO.setFromCurrency("mad");
        dealDTO.setToCurrency("mad1");
        CurrencyPatternException exception2 = assertThrows(CurrencyPatternException.class, ()->{
            dealService.saveDeal(dealDTO);
        });
        assertEquals(exception1.getMessage(), "Currency mad1 must be 3 characters");
        assertEquals(exception2.getMessage(), "Currency mad1 must be 3 characters");
    }

    @Test
    @DisplayName("testing the existence of the deal")
    void testDealExistence(){
        given(dealRepository.existsById("deal-1")).willReturn(true);

        AlreadyExistsException exception = assertThrows(
                AlreadyExistsException.class,
                () -> dealService.saveDeal(dealDTO),
                "Expected saveDeal() to throw AlreadyExistsException if deal already exists"
        );

        assertEquals("Deal with id deal-1 already exists", exception.getMessage());

        verify(dealRepository).existsById("deal-1");
    }

    @Test
    @DisplayName("testing create batch method")
    void testCreateBatch() {
        ArrayList<DealDTO> dealsDTO = new ArrayList<>();
        given(dealRepository.existsById("deal-1")).willReturn(false);
        given(modelMapper.map(dealDTO, Deal.class)).willReturn(deal1);
        given(dealRepository.save(deal1)).willReturn(deal1);
        given(modelMapper.map(deal1, DealResponseDTO.class)).willReturn(dealResponseDTO1);
        given(dealService.saveDeal(dealDTO)).willReturn(dealResponseDTO1);
        dealsDTO.add(dealDTO);
        List<DealResponseDTO> result = dealService.saveBatch(dealsDTO);
        assertEquals(result.size(), 1);
    }

    @Test
    @DisplayName("testing get all deals method")
    void testGetAllDeals() {
            List<Deal> deals = List.of(deal1, deal2);
            List<DealResponseDTO> expectedResponseDTOs = List.of(dealResponseDTO1, dealResponseDTO2);

            given(dealRepository.findAll()).willReturn(deals);
            given(modelMapper.map(deal1, DealResponseDTO.class)).willReturn(dealResponseDTO1);
            given(modelMapper.map(deal2, DealResponseDTO.class)).willReturn(dealResponseDTO2);

            List<DealResponseDTO> actualDeals = dealService.getAllDeals();

            assertEquals(expectedResponseDTOs.size(), actualDeals.size(), "The size of the returned list should match the expected size");
            assertEquals(expectedResponseDTOs, actualDeals, "The returned list of deals should match the expected list");
        }

}

