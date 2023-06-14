package ra.services.seat;

import ra.dtos.SeatDTO;

import java.util.List;

public interface ISeatService {
    List<SeatDTO> getSeatsByScheduleId(Integer scheduleId);
}
