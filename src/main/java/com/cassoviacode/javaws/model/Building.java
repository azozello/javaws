package com.cassoviacode.javaws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Building {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int hitPoints;
    private boolean activated;

    public Building() {
    }

    public Building(String name, int hitPoints, boolean activated) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.activated = activated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", activated=" + activated +
                '}';
    }
}
