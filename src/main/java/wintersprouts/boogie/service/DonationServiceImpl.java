package wintersprouts.boogie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationStatus;
import wintersprouts.boogie.repository.DonationRepository;

import java.time.LocalDate;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    /**
     * 도네이션을 등록합니다.
     */
    @Override
    @Transactional
    public boolean applyDonation(Donation donation) {
        Donation save = donationRepository.save(donation);
        return save.getDonationId() != null;
    }

    /**
     * Donation 의 WAITING Status 를<br>
     * APPORVED 로 변경합니다. 
     */
    @Override
    @Transactional
    public String approved(Long donationIndex) {
        Donation donation = donationRepository.findById(donationIndex).get();
        if (donation.getStatus() == DonationStatus.WAITING) {
            donation.setStatus(DonationStatus.APPROVED);
            donation.setCreatedAt(LocalDate.now());
            return "승인 완료";
        } else {
            return "이미 처리된 요청입니다. ";
        }
    }

    /**
     * 매일 자정마다, 마감일이 지난 Donation 확인하여<br>
     * 상태를 변경해줍니다.
     */
    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void updateExpiredDonations() {
        log.info("Execute Update Expired Donation");
        LocalDate now = LocalDate.now();
        donationRepository.updateExpiredDonations(DonationStatus.FINISH, now);
    }
}
