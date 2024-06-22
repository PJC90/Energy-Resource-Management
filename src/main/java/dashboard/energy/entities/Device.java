package dashboard.energy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dashboard.energy.entities.enums.DeviceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"company", "uninstallation"})
public class Device {
    @Id
    @GeneratedValue
    private UUID deviceId;
    private String deviceNumber;
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    private String plantAddress;
    private LocalDate installation;
    private LocalDate uninstallation;
    private int allarmCount;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "device")
    private List<ConsumptionReading> readings;
}
