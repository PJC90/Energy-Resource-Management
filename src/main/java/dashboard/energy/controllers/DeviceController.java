package dashboard.energy.controllers;

import dashboard.energy.entities.Device;
import dashboard.energy.entities.User;
import dashboard.energy.exceptions.BadRequestException;
import dashboard.energy.payloads.entitiesDTO.DeviceDTO;
import dashboard.energy.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @GetMapping
    public Page<Device> getAllDevice(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "installation") String order){
        return deviceService.getAllDevice(page, size, order);
    }
    @GetMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    public Device findDevice(@PathVariable UUID deviceId){
        return deviceService.findById(deviceId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@AuthenticationPrincipal User user, @RequestBody @Validated DeviceDTO payload, BindingResult validation){
        if(validation.hasErrors()){
            throw  new BadRequestException(validation.getAllErrors());
        }else {
            return deviceService.saveDevice(user, payload);
        }
    }
}
