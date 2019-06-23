package com.cassoviacode.javaws.payload;

import com.cassoviacode.javaws.model.Building;

public class BuildingPayload {
    private String name;
    private int hitPoints;
    private boolean activated;

    public BuildingPayload() {
    }

    public BuildingPayload(String name, int hitPoints, boolean activated) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.activated = activated;
    }

    public BuildingPayload(Building building) {
        this.name = building.getName();
        this.hitPoints = building.getHitPoints();
        this.activated = building.isActivated();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
