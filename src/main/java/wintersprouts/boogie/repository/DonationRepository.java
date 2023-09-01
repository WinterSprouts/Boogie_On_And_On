package wintersprouts.boogie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wintersprouts.boogie.domain.donation.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
