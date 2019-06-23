package com.cassoviacode.javaws.service;

import com.cassoviacode.javaws.model.Building;
import com.cassoviacode.javaws.payload.BuildingPayload;
import com.cassoviacode.javaws.payload.PayloadList;
import com.cassoviacode.javaws.repository.BuildingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BuildingService {

    private BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository repository) {
        this.buildingRepository = repository;
    }

    @Transactional
    public PayloadList<BuildingPayload> getAllBuildingsAsPayloadList(Boolean isActivated) {
        try (Stream<Building> buildingStream = this.buildingRepository.findAllByActivated(isActivated)) {
            return new PayloadList<>(
                    buildingStream
                            .map(BuildingPayload::new)
                            .collect(Collectors.toList()));
        }
    }
}
