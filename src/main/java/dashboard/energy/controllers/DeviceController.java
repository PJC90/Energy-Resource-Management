package dashboard.energy.controllers;

import dashboard.energy.entities.Device;
import dashboard.energy.entities.User;
import dashboard.energy.payloads.entitiesDTO.DeviceDTO;
import dashboard.energy.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@AuthenticationPrincipal User user, @RequestBody DeviceDTO payload){
        return deviceService.saveDevice(user, payload);
    }
}
