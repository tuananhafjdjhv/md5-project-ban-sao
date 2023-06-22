package ra.services.bill;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.dtos.BookingRequestDTO;
import ra.entities.entity.Bill;
import ra.entities.entity.Schedule;
import ra.entities.entity.Ticket;
import ra.repositories.IBillRepository;
import ra.repositories.IScheduleRepository;
import ra.repositories.ISeatRepository;
import ra.repositories.ITicketRepository;
import ra.securities.model.User;
import ra.securities.reponsitory.IUserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService {
    @Autowired
    private IScheduleRepository scheduleRepository;
    @Autowired
    private ITicketRepository ticketRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ISeatRepository seatRepository;
    @Autowired
    private IBillRepository billRepository;


    @Override
    @Transactional
    public void createNewBill(BookingRequestDTO bookingRequestDTO) throws RuntimeException {

        //Lấy ra lịch
        Schedule schedule = scheduleRepository.findById(bookingRequestDTO.getScheduleId()).get();

        //Lấy ra người dùng
        User user = userRepository.findById(bookingRequestDTO.getUserId()).get();

        //Lưu Bill gồm thông tin người dùng xuống trước
        Bill billToCreate = new Bill();
        billToCreate.setUser(user);
        billToCreate.setCreatedTime(LocalDateTime.now());
        Bill createdBill = billRepository.save(billToCreate);

        //Với mỗi ghế ngồi check xem đã có ai đặt chưa, nếu rồi thì throw, roll back luôn còn ko
        //thì đóng gói các thông tin ghế và lịch vào vé và lưu xuống db
        bookingRequestDTO.getListSeatIds().forEach(seatId -> {
            List<Ticket> ticketList = ticketRepository.findTicketBySeatAndSchedule(seatRepository.findById(seatId).get(),schedule);
            if (!ticketList.isEmpty()) {// Nếu đã có người đặt vé ghế đó ở lịch cụ thể đó thì
                throw new RuntimeException("Đã có người nhanh tay hơn đặt ghế, mời bạn chọn lại!");
            }
            // đóng gói lịch, seat và bill vào từng vé rồi add vào list vé

            Ticket ticket = new Ticket();
            ticket.setSchedule(schedule);
            ticket.setSeat(seatRepository.findById(seatId).get());
            ticket.setBill(createdBill);
            ticket.setQrImageURL("https://upload.wikimedia.org/wikipedia/commons/d/d0/QR_code_for_mobile_English_Wikipedia.svg");
            ticketRepository.save(ticket);
        });
    }
}
