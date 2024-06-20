package dashboard.energy.payloads.entitiesDTO;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ConsumptionThresholdDTO(
        @NotNull(message = "La soglia del consumo è un campo obbligatorio!")
        int consumptionThreshold,
        @NotNull(message = "l'id del dispositivo è un campo obbligatorio!")
        UUID deviceId
) {
}
