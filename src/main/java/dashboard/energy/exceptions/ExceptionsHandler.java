package dashboard.energy.exceptions;

import dashboard.energy.payloads.errorDTO.ErrorDTO;
import dashboard.energy.payloads.errorDTO.ErrorDTOlist;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public ErrorDTOlist handleBadRequest(BadRequestException ex){
        List<String> errMessage = new ArrayList<>();
        if(ex.getErrorList() != null)
            errMessage = ex.getErrorList().stream().map(err->err.getDefaultMessage()).toList();
        return new ErrorDTOlist(ex.getMessage(), LocalDateTime.now(), errMessage);
    }
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  //401
    public ErrorDTO handleUnauthorized(UnauthorizedException ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(AccessDeniedException.class) // AccessDeniedException --> org.springframework.security.access.
    @ResponseStatus(HttpStatus.FORBIDDEN)  //403
    public ErrorDTO handleAccessDenied(AccessDeniedException ex){
        return new ErrorDTO("Il tuo ruolo non permette di accedere a questa funzionalità", LocalDateTime.now());
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  //404 not found
    public ErrorDTO handleNotFound(NotFoundException ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleGenericError(Exception ex){
        ex.printStackTrace();
        return new ErrorDTO("Problema lato server...", LocalDateTime.now());
    }
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)  // 403 Forbidden
    public ErrorDTO handleForbidden(IllegalStateException ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }
}
