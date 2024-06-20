package dashboard.energy.controllers;


import dashboard.energy.entities.ConsumptionReading;
import dashboard.energy.entities.Device;
import dashboard.energy.payloads.entitiesDTO.ConsumptionThresholdDTO;
import dashboard.energy.services.ConsumptionReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reading")
public class ConsumptionReadingController {
    @Autowired
    public ConsumptionReadingService consumptionReadingService;

    @GetMapping("/lastReading/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Long> getLastReading(@PathVariable UUID deviceId){
        return consumptionReadingService.getLastReading(deviceId);
    }
    @GetMapping("/consumptionThreshold/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Long> getLastConsumptionThreshold(@PathVariable UUID deviceId){
        return consumptionReadingService.getLastConsumptionThreshold(deviceId);
    }
    @PostMapping("/changeThreshold")
    public void changeConsumptionThreshold(@RequestBody ConsumptionThresholdDTO body){
        consumptionReadingService.changeConsumptionThreshold(body);
    }
}
