package dashboard.energy.services;

import dashboard.energy.entities.ConsumptionReading;
import dashboard.energy.entities.Device;
import dashboard.energy.exceptions.NotFoundException;
import dashboard.energy.payloads.entitiesDTO.ConsumptionThresholdDTO;
import dashboard.energy.repositories.ConsumptionReadingDAO;
import dashboard.energy.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@EnableScheduling // configurazione di Spring x pianificare attività rilevando metodi annotati con @Scheduled in tutta l'applicazione
public class ConsumptionReadingService {
    @Autowired
    private ConsumptionReadingDAO consumptionReadingDAO;
    @Autowired
    private DeviceDAO deviceDAO;

    public void generateReading(Device device){
        Random rm = new Random();
        long lastReading = consumptionReadingDAO.getLastReading(device).orElse(0L);
        long newReading = lastReading + rm.nextInt(4000);
        long consumption = newReading - lastReading;
        int temp = rm.nextInt(19, 40);

        ConsumptionReading consumptionReading= new ConsumptionReading();
        consumptionReading.setDevice(device);
        consumptionReading.setDate(LocalDateTime.now());
        consumptionReading.setReadingValue(newReading);
        consumptionReading.setConsumptionValue(consumption);
        consumptionReading.setTemperature(temp);
        consumptionReading.setConsumptionThreshold(3000);

        consumptionReadingDAO.save(consumptionReading);
    }
    public Optional<Long> getLastReading(UUID deviceId){
        return consumptionReadingDAO.getLastReading(deviceId);
    }
    public void generateReadingAllDevice(){
        List<Device> devices = deviceDAO.findAll();
        for(Device device : devices){
            generateReading(device);
        }
    }
//    @Scheduled(fixedRate = 60000) // Metodo pianificato che viene eseguito ogni 60 secondi generando automaticamente una nuova lettura del dispositivo.
//    public void generateAutomaticallyReadings() {
//        generateReadingAllDevice();
//    }

    public List<ConsumptionReading> getAllReading(){
        return consumptionReadingDAO.findAll();
    }
    public Optional<Long> getLastConsumptionThreshold(UUID readingId){
        return consumptionReadingDAO.getLastConsumptionThreshold(readingId);
    }
    public void changeConsumptionThreshold(ConsumptionThresholdDTO body){
        Optional<ConsumptionReading> reading = consumptionReadingDAO.findLastReading(body.deviceId());
        ConsumptionReading lastReading = reading.get();
        Random rm = new Random();
        long lastreading = lastReading.getReadingValue();
        long random = rm.nextInt(500,2500);
        long read = lastreading + random;
        long consumption = read - lastreading;
        if(reading.isPresent()){
            ConsumptionReading newReading = new ConsumptionReading();
            newReading.setDevice(lastReading.getDevice());
            newReading.setDate(LocalDateTime.now());
            newReading.setReadingValue(read);
            newReading.setConsumptionValue(consumption);
            newReading.setTemperature(lastReading.getTemperature());
            newReading.setConsumptionThreshold(body.consumptionThreshold());
            consumptionReadingDAO.save(newReading);
        } else {
            throw new RuntimeException("Non ci sono letture sul dispositivo con id " + body.deviceId());
        }
    }
}
