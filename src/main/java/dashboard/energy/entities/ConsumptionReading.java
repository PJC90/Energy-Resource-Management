package dashboard.energy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"device"})
public class ConsumptionReading {
    @Id
    @GeneratedValue
    private UUID readingId;
    private LocalDateTime date;
    private long readingValue;
    private long consumptionValue;
    private int temperature;
    private int consumptionThreshold;
    private boolean allarmConsumption = false;
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
}
