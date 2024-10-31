package org.example.fxdatawarehouse.Controllers;

import lombok.AllArgsConstructor;
import org.example.fxdatawarehouse.DTO.DealDTO;
import org.example.fxdatawarehouse.Services.DealService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/deal")
public class DealController {
    private DealService dealService;

    @GetMapping("/")
    public ArrayList<DealDTO> getAllDeals() {
        return null;
    }

    @PostMapping("/")
    public DealDTO createDeal(@RequestBody DealDTO dealDTO) {
        return null;
    }
}
