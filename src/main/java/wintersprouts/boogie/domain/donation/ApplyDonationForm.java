package wintersprouts.boogie.domain.donation;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ApplyDonationForm {
    @NotEmpty(message = "제목은 필수 사항입니다.")
    private String title;

    @NotEmpty(message = "내용은 필수 사항입니다. ")
    @Size(min = 10, message = "내용은 10글자 이상이여야 합니다. ")
    private String content;

    @NotNull(message = "목표 금액은 필수 사항입니다. ")
    private int targetAmount;

    @Future(message = "마감일은 과거일 수 없습니다. ")
    private LocalDate dueDate;
}
