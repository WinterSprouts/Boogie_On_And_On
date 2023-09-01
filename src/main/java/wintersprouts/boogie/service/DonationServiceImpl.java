package wintersprouts.boogie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.repository.DonationRepository;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    @Override
    public boolean applyDonation(Donation donation) {
        Donation save = donationRepository.save(donation);
        return save.getDonationId() != null;
    }
}
