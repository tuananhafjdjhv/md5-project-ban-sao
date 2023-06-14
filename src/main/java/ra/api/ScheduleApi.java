package ra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ra.dtos.ScheduleDTO;
import ra.services.schedule.IScheduleService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping( value="/api/schedules", produces = "application/json")
@CrossOrigin("*")
public class ScheduleApi {
    @Autowired
    private IScheduleService scheduleService;

    @GetMapping("/start-times/{movieId}/{branchId}/{startDate}")
    public List<String> getStartTimes(@PathVariable Integer movieId, @PathVariable Integer branchId,
                                      @PathVariable String startDate) {
        return scheduleService.getStartTimes(movieId,branchId, LocalDate.parse(startDate));
    }

    @GetMapping
    public List<ScheduleDTO> getSchedules(@RequestParam String movieId, @RequestParam String branchId,
                                          @RequestParam String startDate, @RequestParam String startTime,
                                          @RequestParam String roomId){
        return scheduleService.getSchedules(Integer.valueOf(movieId), Integer.valueOf(branchId),startDate,startTime, Integer.valueOf(roomId));
    }
}
