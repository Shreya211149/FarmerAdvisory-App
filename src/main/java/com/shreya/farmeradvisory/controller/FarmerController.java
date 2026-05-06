package com.shreya.farmeradvisory.controller;

import com.shreya.farmeradvisory.dto.request.FarmerGroupRequest;
import com.shreya.farmeradvisory.dto.request.FarmerRequest;
import com.shreya.farmeradvisory.dto.request.LoginRequest;
import com.shreya.farmeradvisory.dto.response.AuthResponse;
import com.shreya.farmeradvisory.dto.response.FarmerGroupResponse;
import com.shreya.farmeradvisory.dto.response.FarmerResponse;
import com.shreya.farmeradvisory.models.Farmer;
import com.shreya.farmeradvisory.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmers")
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;

    @GetMapping
    public List<FarmerResponse> getAllFarmers() {
        return farmerService.getAllFarmers();
    }

    @GetMapping("/{id}")
    public FarmerResponse getFarmer(@PathVariable Long id) {
        return farmerService.getFarmerById(id);
    }

    @GetMapping("/district/{district}")
    public List<FarmerResponse> getByDistrict(@PathVariable String district) {
        return farmerService.getFarmersByDistrict(district);
    }

    @PostMapping
    public FarmerResponse createFarmer(@RequestBody FarmerRequest request) {
        return farmerService.createFarmer(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return farmerService.login(request);
    }

    @DeleteMapping("/{id}")
    public void deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
    }

}
