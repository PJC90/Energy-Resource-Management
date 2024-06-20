package dashboard.energy.payloads.errorDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorDTOlist(
        String message,
        LocalDateTime time,
        List<String> errorsList
) {
}
