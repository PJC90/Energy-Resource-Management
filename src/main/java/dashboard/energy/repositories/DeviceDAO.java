package dashboard.energy.repositories;

import dashboard.energy.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceDAO extends JpaRepository<Device, UUID> {
}
