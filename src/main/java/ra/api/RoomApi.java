package ra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ra.dtos.RoomDTO;
import ra.services.room.IRoomService;

import java.util.List;

@RestController
@RequestMapping( value="/api/rooms", produces = "application/json")
@CrossOrigin("*")
public class RoomApi {
    @Autowired
    private IRoomService roomService;

    @GetMapping("/{movieId}/{branchId}/{startDate}/{startTime}")
    public List<RoomDTO> getRooms(@PathVariable String movieId, @PathVariable String branchId,
                                  @PathVariable String startDate, @PathVariable String startTime){
        return roomService.getRooms(Integer.valueOf(movieId), Integer.valueOf(branchId), startDate, startTime);
    }
}
