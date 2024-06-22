package dashboard.energy.services;

import dashboard.energy.entities.Company;
import dashboard.energy.entities.Device;
import dashboard.energy.entities.User;
import dashboard.energy.entities.enums.DeviceType;
import dashboard.energy.exceptions.NotFoundException;
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

    public Device findById(UUID deviceId){
        return deviceDAO.findById(deviceId).orElseThrow(()->new NotFoundException(deviceId));
    }
    public Page<Device> getMyAllDevice(int size, int page, String order, User user){
        Pageable pageable = PageRequest.of(size, page, Sort.by(order));
        Company company = user.getCompany();
        return deviceDAO.findDeviceByCompany(company, pageable);
    }
    public Device saveDevice(User user, DeviceDTO body){
        Company company = user.getCompany();
        Device newDevice = new Device();
        newDevice.setDeviceNumber(body.deviceNumber());
        newDevice.setPlantAddress(body.plantAddress());
        newDevice.setDeviceType(body.deviceType());
        newDevice.setInstallation(LocalDate.now());
        newDevice.setCompany(company);
        newDevice.setAllarmCount(0);
        return deviceDAO.save(newDevice);
    }
}
