# ADR 0001 – Measurements Domain Model (User, MeasurementSet, Measurement)

## Context
StitchStack stores multiple body measurement sets per user (e.g. "Default", "Summer 2026").
These measurement sets are used as input for garment pattern generation (MVP: pants) and later for fit analysis and visualization.

Key requirements:

1. **Type safety and data consistency**  
   Measurements should not be stored using arbitrary string keys because this leads to errors and poor refactorability.

2. **Extensibility**  
   The system should allow adding new measurement types (e.g. calf circumference) without breaking existing functionality.

3. **Clear domain structure**  
   Measurements should be modeled in a way that reflects real-world concepts in tailoring.

## Decision
Measurements are modeled in the domain as:

- `User` owns multiple `MeasurementSet`
- `MeasurementSet` contains a list of `Measurement`
- `Measurement` consists of:
    - `MeasurementType` (Enum)
    - `valueMm` (numeric value stored in millimeters)

### Domain Structure
