package dashboard.energy.payloads.entitiesDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CompanyDTO(
        @NotEmpty(message = "Nome azienda è obbligatorio")
        @Size(min = 3, max = 30, message = "Il Nome azienda deve avere minimo 3 caratteri, massimo 30")
        String name,
        @NotEmpty(message = "L'indirizzo è obbligatorio")
        @Size(min = 3, max = 60, message = "L'indirizzo deve avere minimo 3 caratteri, massimo 60")
        String address,
        @NotEmpty(message = "La partita IVA dell'azienda è obbligatoria")
        @Size(min = 3, max = 20, message = "La partita IVA minimo 3 caratteri, massimo 20")
        String companyNumber
) {
}
