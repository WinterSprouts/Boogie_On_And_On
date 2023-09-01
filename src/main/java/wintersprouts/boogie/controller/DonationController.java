package wintersprouts.boogie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wintersprouts.boogie.domain.donation.ApplyDonationForm;

import javax.validation.Valid;
import java.net.http.HttpResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/donation")
public class DonationController {

    @PostMapping("/apply")
    public HttpResponse applyDonatio(@RequestBody @Valid ApplyDonationForm form){

    }


}
