package dashboard.energy.services;

import dashboard.energy.entities.Company;
import dashboard.energy.entities.Device;
import dashboard.energy.entities.User;
import dashboard.energy.entities.enums.DeviceType;
import dashboard.energy.payloads.entitiesDTO.DeviceDTO;
import dashboard.energy.repositories.DeviceDAO;
import dashboard.energy.repositories.UserDAO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;

    public Page<Device> getAllDevice(int size, int page, String installation){
        Pageable pageable = PageRequest.of(size, page, Sort.by(installation));
        return deviceDAO.findAll(pageable);
    }
    public Device saveDevice(User user, DeviceDTO body){
        Company company = user.getCompany();
        Device newDevice = new Device();
        newDevice.setDeviceNumber(body.deviceNumber());
        newDevice.setPlantAddress(body.plantAddress());
        newDevice.setDeviceType(DeviceType.DOMESTIC);
        newDevice.setInstallation(LocalDate.now());
        newDevice.setConsumptionThreshold(1000);
        newDevice.setCompany(company);
        return deviceDAO.save(newDevice);
    }
}
