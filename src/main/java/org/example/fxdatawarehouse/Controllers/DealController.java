package org.example.fxdatawarehouse.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.fxdatawarehouse.DTO.DealDTO;
import org.example.fxdatawarehouse.DTO.DealResponseDTO;
import org.example.fxdatawarehouse.Services.DealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/deal")
public class DealController {
    private DealService dealService;

    @GetMapping
    public ArrayList<DealResponseDTO> getAllDeals() {
        return (ArrayList<DealResponseDTO>) dealService.getAllDeals();
    }

    @PostMapping
    public ResponseEntity<DealResponseDTO> createDeal(@Valid @RequestBody DealDTO dealDTO) {
        return new ResponseEntity<>(dealService.saveDeal(dealDTO), HttpStatus.CREATED);
    }

    @PostMapping("/batchSave")
    public ResponseEntity<List<DealResponseDTO>> batchCreate(@Valid @RequestBody ArrayList<DealDTO> dealDTOs) {
        return new ResponseEntity<>(dealService.saveBatch(dealDTOs), HttpStatus.CREATED);
    }
}
