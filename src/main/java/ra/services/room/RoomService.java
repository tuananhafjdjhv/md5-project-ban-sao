//package ra.services.room;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ra.dtos.RoomDTO;
//import ra.entities.entity.Room;
//import ra.entities.entity.Schedule;
//import ra.repositories.IRoomRepository;
//import ra.repositories.IScheduleRepository;
//import ra.services.schedule.IScheduleService;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class RoomService implements IRoomService{
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private IScheduleRepository scheduleRepository;
//    @Autowired
//    private IRoomRepository roomRepository;
//    @Autowired
//    private IRoomService roomService;
//    @Override
//    public List<RoomDTO> getRooms(Integer movieId, Integer branchId, String startDate ) {
//
//        List<RoomDTO> list = roomRepository.getRoomByBranchAndMovieAndSchedule(movieId,
//                        branchId, LocalDate.parse(startDate))
//                .stream().map(room -> modelMapper.map(room,RoomDTO.class))
//                .collect(Collectors.toList());
//        return list;
//    }
//}
