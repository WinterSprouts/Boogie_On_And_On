package wintersprouts.boogie.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;
import org.springframework.stereotype.Repository;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationSearchCondition;
import wintersprouts.boogie.domain.donation.QDonation;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DonationRepositoryCustomImpl implements DonationRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Donation> searchByConditions(DonationSearchCondition condition) {
        QDonation donation = QDonation.donation;

        BooleanBuilder builder = new BooleanBuilder();

        if(condition.getTitle() != null){
            builder.and(donation.title.contains(condition.getTitle()));
        }
        if(condition.getContent() != null){
            builder.and(donation.title.contains(condition.getContent()));
        }

        return jpaQueryFactory.selectFrom(donation)
                .where(builder)
                .fetch();
    }
}
