package dashboard.energy.payloads.entitiesDTO;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UserDTO(
        @NotEmpty(message = "username è obbligatorio")
        @Size(min = 3, max = 30, message = "username deve avere minimo 3 caratteri, massimo 30")
        String username,
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 30, message = "Il Nome deve avere minimo 3 caratteri, massimo 30")
        String name,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 30, message = "Il Cognome deve avere minimo 3 caratteri, massimo 30")
        String surname,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @Past(message = "La data di nascita deve essere nel passato")
        LocalDate birthday,
        @Email(message = "L'indirizzo email inserito non è un indirizzo valido")
        @NotNull(message = "La mail è un campo obbligatorio!")
        String email,
        @NotEmpty(message = "La password è obbligatoria")
        @Size(min = 5, max = 30, message = "La password deve avere minimo 5 caratteri, massimo 30")
        String password
) {
}
