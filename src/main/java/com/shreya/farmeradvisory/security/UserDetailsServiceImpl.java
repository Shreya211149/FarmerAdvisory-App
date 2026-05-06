package com.shreya.farmeradvisory.security;

import com.shreya.farmeradvisory.exception.FarmerNotFoundException;
import com.shreya.farmeradvisory.models.Farmer;
import com.shreya.farmeradvisory.repo.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final FarmerRepository farmerRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Farmer farmer = farmerRepository.findByUsername(username)
                .orElseThrow(() ->
                        new FarmerNotFoundException(
                                "Farmer not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(farmer.getUsername())
                .password(farmer.getPassword())
                .authorities("ROLE_FARMER")
                .build();
    }
}