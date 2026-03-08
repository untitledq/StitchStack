package com.stitchstack.domain.model;

import com.stitchstack.domain.enums.MeasurementType;

import java.util.List;
import java.util.UUID;

public class MeasurementSet {

    private UUID id;
    private UUID creatorId;
    private String name;
    private List<Measurement> measurements;

    public Measurement getMeasurement(MeasurementType measurementType) {
        if(measurements == null){
            return null;
        }
        for(Measurement measurement : measurements){
            if(measurement.getType().equals(measurementType)){
                return measurement;
            }
        }
        return null;
    }

    //TODO: addMeasurement will be added later


    public void setId(UUID id) {
        if (id == null) throw new IllegalArgumentException("id must not be null");
        this.id = id;
    }
    public UUID getId() {
        return id;
    }

    public void setCreatorId(UUID creatorId) {
        if (creatorId == null) throw new IllegalArgumentException("creatorId must not be null");
        this.creatorId = creatorId;
    }
    public UUID getCreatorId() {
        return creatorId;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("name must not be null");
        this.name = name;
    }
    public String getName() {
        return name;
    }

}

