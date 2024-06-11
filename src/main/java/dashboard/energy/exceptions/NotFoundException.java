package dashboard.energy.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){super("Elemento con id: " + id + " non trovato.");}
    public NotFoundException(String message){super(message);}
}
