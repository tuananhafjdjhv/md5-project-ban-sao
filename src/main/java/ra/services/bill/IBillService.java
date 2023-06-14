package ra.services.bill;

import ra.dtos.BookingRequestDTO;

public interface IBillService {
    void createNewBill(BookingRequestDTO bookingRequestDTO) throws RuntimeException;
}
