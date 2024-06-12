package dashboard.energy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue
    private UUID companyId;
    private String name;
    private String address;
    private String companyNumber;
    private String logo;
    @OneToMany(mappedBy = "company")
    private List<User> users;
    @OneToMany(mappedBy = "company")
    private List<Device> devices;
}
