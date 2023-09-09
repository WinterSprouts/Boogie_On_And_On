package wintersprouts.boogie.repository;

import org.springframework.stereotype.Repository;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationSearchCondition;

import java.util.List;

@Repository
public class DonationRepositoryCustomImpl implements DonationRepositoryCustom{
    @Override
    public List<Donation> getDanationByCondition(DonationSearchCondition condition) {
        return null;
    }
}
