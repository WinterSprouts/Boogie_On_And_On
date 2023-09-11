package wintersprouts.boogie.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationSearchCondition;
import wintersprouts.boogie.domain.donation.QDonation;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DonationRepositoryCustomImpl implements DonationRepositoryCustom {

    private final JPAQueryFactory query;

    public DonationRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    /**
     * Condition 을 활용해 동적쿼리를 생성합니다.
     *
     * @param condition
     * @return
     */
    @Override
    public List<Donation> searchByConditions(DonationSearchCondition condition) {
        QDonation donation = QDonation.donation;

        List<Donation> result = query
                .select(donation)
                .from(donation)
                .fetch();

        return result;
    }

}
