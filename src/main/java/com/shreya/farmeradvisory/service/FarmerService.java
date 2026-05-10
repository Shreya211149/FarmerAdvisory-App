package com.shreya.farmeradvisory.service;

import com.shreya.farmeradvisory.dto.request.FarmerRequest;
import com.shreya.farmeradvisory.dto.request.LoginRequest;
import com.shreya.farmeradvisory.dto.response.AuthResponse;
import com.shreya.farmeradvisory.dto.response.FarmerResponse;
import com.shreya.farmeradvisory.exception.FarmerNotFoundException;
import com.shreya.farmeradvisory.exception.GroupNotFoundException;
import com.shreya.farmeradvisory.models.Farmer;
import com.shreya.farmeradvisory.models.FarmerGroup;
import com.shreya.farmeradvisory.repo.FarmerGroupRepository;
import com.shreya.farmeradvisory.repo.FarmerRepository;
import com.shreya.farmeradvisory.security.JwtService;
import com.shreya.farmeradvisory.transformer.FarmerTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmerService {
    private final FarmerRepository farmerRepository;
    private final FarmerGroupRepository groupRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;  // ← add
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Farmer farmer = farmerRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new FarmerNotFoundException("Farmer not found"));

        String token = jwtService.generateToken(farmer.getUsername());
        return new AuthResponse(token, farmer.getUsername(), farmer.getDistrict(), "ROLE_FARMER");
    }

    public List<FarmerResponse> getAllFarmers() {
        return farmerRepository.findAll()
                .stream()
                .map(FarmerTransformer::toResponse)
                .collect(Collectors.toList());
    }
    public FarmerResponse getFarmerById(Long id) {
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new FarmerNotFoundException("Farmer not found: " + id));
        return FarmerTransformer.toResponse(farmer);
    }
    public FarmerResponse createFarmer(FarmerRequest request) {
        FarmerGroup group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new GroupNotFoundException("Group not found: " + request.getGroupId()));
        Farmer farmer = FarmerTransformer.toEntity(request, group,passwordEncoder);
        return FarmerTransformer.toResponse(farmerRepository.save(farmer));
    }

    public List<FarmerResponse> getFarmersByDistrict(String district) {
        return farmerRepository.findByDistrict(district)
                .stream()
                .map(FarmerTransformer::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }

}
