package dashboard.energy.repositories;

import dashboard.energy.entities.ConsumptionReading;
import dashboard.energy.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConsumptionReadingDAO extends JpaRepository<ConsumptionReading, UUID> {
    @Query("SELECT a.readingValue FROM ConsumptionReading a WHERE a.device = :device ORDER BY a.date DESC LIMIT 1")
    Optional<Long> getLastReading(@Param("device") Device device);

    @Query("SELECT a.readingValue FROM ConsumptionReading a WHERE a.device.deviceId = :deviceId ORDER BY a.date DESC LIMIT 1")
    Optional<Long> getLastReading(@Param("deviceId") UUID deviceId);
    @Query("SELECT a.consumptionThreshold FROM ConsumptionReading a WHERE a.device.deviceId = :deviceId ORDER BY a.date DESC LIMIT 1")
    Optional<Long> getLastConsumptionThreshold(@Param("deviceId") UUID deviceId);
    @Query("SELECT cr FROM ConsumptionReading cr WHERE cr.device.deviceId = :deviceId ORDER BY cr.date DESC LIMIT 1")
    Optional<ConsumptionReading> findLastReading(UUID deviceId);
}
