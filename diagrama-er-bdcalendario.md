```mermaid
erDiagram
    TIPO ||--o{ CALENDARIO : clasifica

    TIPO {
        int Id PK
        varchar Tipo
    }

    CALENDARIO {
        int Id PK
        date Fecha
        int IdTipo FK
        varchar Descripcion
    }
```
