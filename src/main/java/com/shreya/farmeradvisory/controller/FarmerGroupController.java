package com.shreya.farmeradvisory.controller;

import com.shreya.farmeradvisory.dto.request.FarmerGroupRequest;
import com.shreya.farmeradvisory.dto.response.FarmerGroupResponse;
import com.shreya.farmeradvisory.service.FarmerGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class FarmerGroupController {
    private final FarmerGroupService groupService;

    @GetMapping
    public List<FarmerGroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }
    @GetMapping("/{id}")
    public FarmerGroupResponse getGroup(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }
    @PostMapping
    public FarmerGroupResponse createGroup(@RequestBody FarmerGroupRequest request) {
        return groupService.createGroup(request);
    }
    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
}
