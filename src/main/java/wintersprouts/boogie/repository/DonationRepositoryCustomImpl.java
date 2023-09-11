package wintersprouts.boogie.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationCurationForm;
import wintersprouts.boogie.domain.donation.DonationSearchCondition;
import wintersprouts.boogie.domain.donation.QDonation;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DonationRepositoryCustomImpl implements DonationRepositoryCustom {

    private final JPAQueryFactory query;

    public DonationRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }


    /**
     * QueryDSL 을 통한 동적 쿼리를 생성하고 조회합니다. 
     * @param condition
     * @return
     */
    @Override
    public List<DonationCurationForm> selectByCondition(DonationSearchCondition condition) {
        QDonation donation = QDonation.donation;

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(condition.getContent())) {
            builder.and(donation.content.contains(condition.getContent()));
        }
        if (StringUtils.hasText(condition.getTitle())) {
            builder.and(donation.title.contains(condition.getTitle()));
        }

        List<Donation> fetch = query.select(donation)
                .from(donation)
                .where(builder)
                .fetch();

        return fetch.stream()
                .map(each -> {
                    DonationCurationForm form = new DonationCurationForm();
                    form.setTitle(each.getTitle());
                    form.setContent(each.getContent());

                    return form;
                }).collect(Collectors.toList());
    }
}
