package ra.services.room;

import ra.dtos.RoomDTO;

import java.util.List;

public interface IRoomService {
    List<RoomDTO> getRooms(Integer movieId,Integer branchId,String startDate,String startTime);
}
