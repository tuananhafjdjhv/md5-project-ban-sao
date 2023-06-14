package ra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ra.dtos.SeatDTO;
import ra.services.seat.ISeatService;

import java.util.List;

@RestController
@RequestMapping( value="/api/seats", produces = "application/json")
@CrossOrigin("*")
public class SeatApi {
    @Autowired
    private ISeatService seatService;

    @GetMapping("/{scheduleId}")
    public List<SeatDTO> getSeatsByScheduleId(@PathVariable String scheduleId){
        return seatService.getSeatsByScheduleId(Integer.valueOf(scheduleId));
    }
}
