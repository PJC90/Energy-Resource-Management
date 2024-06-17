package dashboard.energy.controllers;

import dashboard.energy.entities.Device;
import dashboard.energy.entities.User;
import dashboard.energy.payloads.entitiesDTO.DeviceDTO;
import dashboard.energy.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @GetMapping
    public Page<Device> getAllDevice(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "installation") String installation){
        return deviceService.getAllDevice(page, size, installation);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@AuthenticationPrincipal User user, @RequestBody DeviceDTO payload){
        return deviceService.saveDevice(user, payload);
    }
}
