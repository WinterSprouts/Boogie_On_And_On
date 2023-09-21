package wintersprouts.boogie.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import wintersprouts.boogie.auth.JwtTools;
import wintersprouts.boogie.domain.donation.ApplyDonationForm;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationStatus;
import wintersprouts.boogie.domain.donation.DonationUpdateForm;
import wintersprouts.boogie.domain.member.Member;
import wintersprouts.boogie.repository.MemberRepository;
import wintersprouts.boogie.service.DonationService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/donation")
public class DonationController {

    private final JwtTools jwtTools;
    private final DonationService donationService;
    private final MemberRepository memberRepository;

    /**
     * Auth ROLE_ORGANIZATION
     * 도네이션 등록
     */
    @PostMapping("/apply")
    public ResponseEntity<Void> applyDonation(@RequestBody @Valid ApplyDonationForm form, HttpServletRequest request) {

        Member publisher = memberRepository.findByEmail(jwtTools.getMemberEmailByRequest(request)).get();
        Donation applyDonation = Donation.builder().donationPublisher(publisher).title(form.getTitle()).content(form.getContent()).targetAmount(form.getTargetAmount()).dueDate(form.getDueDate()).status(DonationStatus.WAITING).build();

        boolean isApplySuccess = donationService.applyDonation(applyDonation);

        return isApplySuccess ? ResponseEntity.accepted().build() : ResponseEntity.badRequest().build();
    }

    /**
     * Auth ROLE_ADMIN
     * 도네이션 승인
     */
    @PostMapping("/approved")
    public ResponseEntity<String> approveDonation(@RequestBody @Valid DonationUpdateForm form) {
        String message = donationService.approved(form.getDonationIndex());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Donation>> findAll() {
        List<Donation> all = donationService.findAll();

        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> findOne(@PathVariable Long id) {
        Donation donation = donationService.findOne(id);

        return ResponseEntity.ok().body(donation);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> donate(@PathVariable Long id) {

        return null;
    }


}
