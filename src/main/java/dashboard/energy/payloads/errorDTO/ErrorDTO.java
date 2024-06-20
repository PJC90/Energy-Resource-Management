package dashboard.energy.payloads.errorDTO;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime time) {
}
