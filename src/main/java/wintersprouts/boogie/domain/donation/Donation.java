package wintersprouts.boogie.domain.donation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import wintersprouts.boogie.domain.donator.Donator;
import wintersprouts.boogie.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="DONATION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DONATION_ID")
    private Long donationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    @Column(name="DONATION_PUBLISHER")
    private Member donationPublisher;

    @Column(name="DONATION_TITLE")
    private String title;

    @Column(name="DONATION_CONTENT")
    private String content;

    @Column(name="DONATION_CURRENT_AMOUNT")
    private int currentAmount;

    @Column(name="DONATION_TARGET_AMOUNT")
    private int targetAmount;

    @Column(name = "DONATION_DUE_DATE")
    private LocalDate dueDate;

    @Column(name="DONATION_STATUS")
    @Enumerated
    private DonationStatus status;

    @Column(name="DONATION_CREATE_AT")
    private LocalDate createdAt;

    @Column(name="DONATION_MODIFIED_AT")
    private LocalDate modifiedAt;

    /**
     * 이 기부에 기부한 사람 목록
     */
    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name="DONATOR_PUBLISHER_ID")
    @OneToMany(mappedBy = "donation")
    private List<Donator> donators = new ArrayList<>();

    @Builder
    public Donation(Member donationPublisher, String title, String content, int currentAmount, int targetAmount, LocalDate dueDate, LocalDate createdAt, LocalDate modifiedAt, DonationStatus status) {
        this.donationPublisher = donationPublisher;
        this.title = title;
        this.content = content;
        this.currentAmount = currentAmount;
        this.targetAmount = targetAmount;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.status = status;
    }
}
