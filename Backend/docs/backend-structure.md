# Backend Struktur – StitchStack

## Ziel

Sauberes, minimalistisches Backend für:

- Auth
- Measurement-Sets
- Erweiterbarkeit Richtung Pattern-Generierung

## Package-Struktur

com.stitchstack

- domain
  - model
  - dto
  - ports

- application
  - Services

- infrastructure
  - http
  - persistence
  - security
  - config

## Datenbank

### users
- id (UUID)
- username
- password_hash
- created_at

### measurement_sets
- id (UUID)
- user_id (FK)
- name
- values_json (JSONB)
- created_at
- updated_at

## API (MVP)

Auth:
- POST /api/auth/register
- POST /api/auth/login

Measurements:
- GET /api/measurements
- POST /api/measurements
- PUT /api/measurements/{id}
- DELETE /api/measurements/{id}

Pattern:
- POST /api/patterns/pants:generate