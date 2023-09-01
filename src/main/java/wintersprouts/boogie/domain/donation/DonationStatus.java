package wintersprouts.boogie.domain.donation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DonationStatus {
    WAITING("승인 대기중"), APPROVED("모집 중"), FINISH("모집 종료");
    private String value;
}