package com.domain.model;

import java.util.Map;
import java.util.UUID;

public class MeasurementSet {

    private UUID id;
    private UUID userId;
    private String name;

    private Map<String, Double> measurements;
}
