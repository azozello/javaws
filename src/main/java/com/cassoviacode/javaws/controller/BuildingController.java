package com.cassoviacode.javaws.controller;

import com.cassoviacode.javaws.payload.BuildingPayload;
import com.cassoviacode.javaws.payload.PayloadList;
import com.cassoviacode.javaws.service.BuildingService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BuildingController {

    private final BuildingService service;

    public BuildingController(BuildingService service) {
        this.service = service;
    }

    @MessageMapping("/get-all-buildings")
    @SendTo("/topic/city")
    public PayloadList<BuildingPayload> getAllBuildings(boolean isActivated) {
        //TODO implement logic that returns all buildings from database
        return this.service.getAllBuildingsAsPayloadList(isActivated);
    }
}
