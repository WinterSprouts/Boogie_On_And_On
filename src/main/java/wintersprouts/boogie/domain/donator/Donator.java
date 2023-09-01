package wintersprouts.boogie.domain.donator;

import lombok.Builder;
import lombok.Getter;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.member.Member;

import javax.persistence.*;

@Entity
@Table(name = "DONATOR")
@Getter
public class Donator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DONATOR_ID")
    private Long donatorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONATION_ID")
    private Donation donation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(name = "DONATOR_AMOUNT", nullable = false)
    private String donatorAmount;






}
