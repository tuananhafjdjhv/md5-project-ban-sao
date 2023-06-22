//package ra.api;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import ra.dtos.RoomDTO;
//import ra.entities.entity.Schedule;
//import ra.repositories.IScheduleRepository;
//import ra.services.room.IRoomService;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping( value="/api/rooms", produces = "application/json")
//@CrossOrigin("*")
//public class RoomApi {
//    @Autowired
//    private IRoomService roomService;
//
//    @Autowired
//    private IScheduleRepository scheduleRepository;
//
//    @GetMapping("/{movieId}/{branchId}/{startDate}")
//    public List<RoomDTO> getRooms(@PathVariable Integer movieId,
//                                  @PathVariable Integer branchId,
//                                  @PathVariable String startDate
//                                 ){
//        List<RoomDTO> list = roomService.getRooms(movieId,
//                branchId, startDate);
//        return  list;
//    }
//}
