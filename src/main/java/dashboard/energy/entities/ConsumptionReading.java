package dashboard.energy.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ConsumptionReading {
    @Id
    @GeneratedValue
    private UUID readingId;
    private LocalDateTime date;
    private long readingValue;
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
}
