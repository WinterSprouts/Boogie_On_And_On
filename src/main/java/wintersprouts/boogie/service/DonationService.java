package wintersprouts.boogie.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import wintersprouts.boogie.domain.donation.Donation;

import java.util.List;

public interface DonationService {
    public boolean applyDonation(Donation donation);

    String approved(Long donationIndex);

    /**
     * 매일 자정마다, 마감일이 지난 Donation 확인하여<br>
     * 상태를 변경해줍니다.
     */
    void updateExpiredDonations();

    public List<Donation> findAll();

    Donation findOne(Long id);

}
