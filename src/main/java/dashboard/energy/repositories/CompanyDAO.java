package dashboard.energy.repositories;

import dashboard.energy.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyDAO extends JpaRepository<Company, UUID> {
}
