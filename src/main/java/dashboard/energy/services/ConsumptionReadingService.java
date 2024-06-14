package dashboard.energy.services;

import dashboard.energy.entities.ConsumptionReading;
import dashboard.energy.entities.Device;
import dashboard.energy.repositories.ConsumptionReadingDAO;
import dashboard.energy.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

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
        long newReading = lastReading + rm.nextInt(1000);

        ConsumptionReading consumptionReading= new ConsumptionReading();
        consumptionReading.setDevice(device);
        consumptionReading.setDate(LocalDateTime.now());
        consumptionReading.setReadingValue(newReading);

        consumptionReadingDAO.save(consumptionReading);
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
}
