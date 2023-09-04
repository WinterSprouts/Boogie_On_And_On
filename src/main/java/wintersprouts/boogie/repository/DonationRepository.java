package wintersprouts.boogie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationStatus;

import java.time.LocalDate;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Donation d SET d.status = ?1 WHERE d.dueDate < ?2")
    int updateExpiredDonations(DonationStatus status, LocalDate now);
}
