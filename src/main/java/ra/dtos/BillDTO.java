package ra.dtos;

import lombok.Data;
import ra.securities.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BillDTO {
    private int id;
    private LocalDateTime createdTime;
    private List<TicketDTO> listTickets;
    private User user;
}
