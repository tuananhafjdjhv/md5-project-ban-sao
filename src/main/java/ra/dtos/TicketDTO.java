package ra.dtos;

import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private String qrImageURL;
    private ScheduleDTO schedule;
    private SeatDTO seat;
    private BillDTO bill;
}
