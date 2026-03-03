# StitchStack

StitchStack verbindet Software Engineering mit dem Handwerk des Nähens.

Es handelt sich um eine browserbasierte Webanwendung zur maßbasierten Schnittmuster-Generierung mit 3D-Preview und visueller Fit-Analyse.

## Idee

Aus gespeicherten Körpermaßen werden parametrische Schnittmuster generiert (MVP: Hose).  
Die Kleidung wird im Browser auf einem Avatar (z.B. MakeHuman-Export als GLB) dargestellt.

Zu enge Bereiche werden mittels Heatmap hervorgehoben.  
Die Fit-Analyse basiert auf einer Distanzmessung zwischen Kleidungs-Vertices und vereinfachten Avatar-Collidern (Capsules).

## MVP-Fokus: Hose

Warum Hose?

- Mehrere Panels (Vorderteil/Hinterteil links/rechts)
- Schrittnaht
- Zwei Beine
- Gute Testbasis für Fit-Analyse

Wenn die Hose funktioniert, ist die Architektur belastbar.

## Architektur

### Frontend (Angular + TypeScript)

- Measurement UI
- Deterministische Pattern Engine
- SVG-Preview
- 3D Viewer (Three.js)
- Fit-Heatmap

### Backend (Java + PostgreSQL)

- Registrierung / Login
- Speicherung mehrerer Measurement-Sets
- Vorbereitung für Pattern-Generate API

## Langfristige Vision

- Weitere Kleidungsstücke
- Cloth-Simulation (XPBD/PBD)
- Erweiterte Fit-Metriken
- Simulation-Provider-Interface
