package wintersprouts.boogie.service;

import wintersprouts.boogie.domain.donation.Donation;

public interface DonationService {
    public boolean applyDonation(Donation donation);

    String approved(Long donationIndex);
}
