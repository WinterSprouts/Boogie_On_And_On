package wintersprouts.boogie.service;

import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationCurationForm;
import wintersprouts.boogie.domain.donation.DonationSearchCondition;

import java.util.List;

import java.util.List;

public interface DonationService {
    public boolean applyDonation(Donation donation);

    String approved(Long donationIndex);

    /**
     * 매일 자정마다, 마감일이 지난 Donation 확인하여<br>
     * 상태를 변경해줍니다.
     */
    void updateExpiredDonations();

    @Deprecated
    List<DonationCurationForm> selectAll();

    List<DonationCurationForm> selectByCondition(DonationSearchCondition condition);

    Donation findOne(Long id);
}
