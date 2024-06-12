package dashboard.energy.payloads.entitiesDTO;

import dashboard.energy.entities.enums.DeviceType;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record DeviceDTO(
        @NotEmpty(message = "La matricola del dispositivo è obbligatorio")
        @Size(min = 3, max = 15, message = "La matricola deve avere minimo 3 caratteri, massimo 15")
        String deviceNumber,
        @NotEmpty(message = "L'indirizzo è obbligatorio")
        @Size(min = 3, max = 60, message = "L'indirizzo deve avere minimo 3 caratteri, massimo 60")
        String plantAddress
) {
}
