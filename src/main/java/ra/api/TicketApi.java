package ra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ra.dtos.TicketDTO;
import ra.services.ticket.ITicketService;

import java.util.List;

@RestController
@RequestMapping( value="/api/tickets", produces = "application/json")
@CrossOrigin("*")
public class TicketApi {
    @Autowired
    private ITicketService ticketService;

    @GetMapping("/{userId}")
    public List<TicketDTO> getTicketsByUserId(@PathVariable String userId){
        return ticketService.getTicketsByUserId(Integer.valueOf(userId));
    }
}
