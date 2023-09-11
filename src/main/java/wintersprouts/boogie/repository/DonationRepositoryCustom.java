package wintersprouts.boogie.repository;

import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationSearchCondition;

import java.util.List;

public interface DonationRepositoryCustom {
    public List<Donation> searchByConditions(DonationSearchCondition condition);
}
