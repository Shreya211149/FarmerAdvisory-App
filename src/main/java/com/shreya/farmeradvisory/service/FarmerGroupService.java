package com.shreya.farmeradvisory.service;

import com.shreya.farmeradvisory.dto.request.FarmerGroupRequest;
import com.shreya.farmeradvisory.dto.response.FarmerGroupResponse;
import com.shreya.farmeradvisory.exception.GroupNotFoundException;
import com.shreya.farmeradvisory.models.FarmerGroup;
import com.shreya.farmeradvisory.repo.FarmerGroupRepository;
import com.shreya.farmeradvisory.transformer.FarmerGroupTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmerGroupService {
    private final FarmerGroupRepository groupRepository;
    public List<FarmerGroupResponse> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(FarmerGroupTransformer::toResponse)
                .collect(Collectors.toList());
    }
    public FarmerGroupResponse getGroupById(Long id) {
        FarmerGroup group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group not found: " + id));
        return FarmerGroupTransformer.toResponse(group);
    }
    public FarmerGroupResponse createGroup(FarmerGroupRequest request) {
        FarmerGroup group = FarmerGroupTransformer.toEntity(request);
        return FarmerGroupTransformer.toResponse(groupRepository.save(group));
    }
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

}
