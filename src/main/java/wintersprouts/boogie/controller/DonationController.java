package wintersprouts.boogie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wintersprouts.boogie.auth.JwtTools;
import wintersprouts.boogie.domain.donation.ApplyDonationForm;
import wintersprouts.boogie.domain.donation.Donation;
import wintersprouts.boogie.domain.donation.DonationStatus;
import wintersprouts.boogie.domain.member.Member;
import wintersprouts.boogie.repository.MemberRepository;
import wintersprouts.boogie.service.DonationService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/donation")
public class DonationController {

    private final JwtTools jwtTools;
    private final DonationService donationService;
    private final MemberRepository memberRepository;

    @PostMapping("/apply")
    public ResponseEntity<Void> applyDonation(@RequestBody @Valid ApplyDonationForm form, HttpServletRequest request) {

        Member publisher = memberRepository.findByEmail(jwtTools.getMemberEmailByRequest(request)).get();
        Donation applyDonation = Donation.builder().donationPublisher(publisher).title(form.getTitle()).content(form.getContent()).targetAmount(form.getTargetAmount()).dueDate(form.getDueDate()).status(DonationStatus.WAITING).build();

        boolean isApplySuccess = donationService.applyDonation(applyDonation);

        return isApplySuccess ? ResponseEntity.accepted().build() : ResponseEntity.badRequest().build();
    }


}
