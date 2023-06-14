package ra.services.ticket;

import ra.dtos.TicketDTO;

import java.util.List;

public interface ITicketService {
    List<TicketDTO> getTicketsByUserId(Integer userId);
}
