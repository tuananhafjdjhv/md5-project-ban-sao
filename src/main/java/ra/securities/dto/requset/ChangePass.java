package ra.securities.dto.requset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ChangePass {
    private String oldPass;
    private String newPass;
    private String rePass;
}
