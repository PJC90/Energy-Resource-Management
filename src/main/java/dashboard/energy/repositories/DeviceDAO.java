package dashboard.energy.repositories;

import dashboard.energy.entities.Company;
import dashboard.energy.entities.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceDAO extends JpaRepository<Device, UUID> {
    @Query("SELECT d FROM Device d WHERE d.company = :company")
    Page<Device> findDeviceByCompany(@Param("company")Company company, Pageable pageable);
}
