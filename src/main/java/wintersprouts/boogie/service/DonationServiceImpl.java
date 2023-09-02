package wintersprouts.boogie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationStatus;
import wintersprouts.boogie.domain.member.Role;
import wintersprouts.boogie.repository.DonationRepository;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    @Override
    public boolean applyDonation(Donation donation) {
        Donation save = donationRepository.save(donation);
        return save.getDonationId() != null;
    }

    @Override
    @Transactional
    public String approved(Long donationIndex) {
        Donation donation = donationRepository.findById(donationIndex).get();
        if(donation.getStatus() == DonationStatus.WAITING){
            donation.setStatus(DonationStatus.APPROVED);
            return "승인 완료";
        }else {
            return "이미 처리된 요청입니다. ";
        }
    }

    /**
     * 매일 자정마다, 마감일이 지난 Donation 확인하여<br>
     * 상태를 변경해줍니다.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    protected void updateExpiredDonations() {
        LocalDate now = LocalDate.now();
        donationRepository.updateExpiredDonations(DonationStatus.FINISH, now);
    }
}
