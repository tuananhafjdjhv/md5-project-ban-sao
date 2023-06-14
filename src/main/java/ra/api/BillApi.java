package ra.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dtos.BillDTO;
import ra.dtos.BookingRequestDTO;
import ra.repositories.IBillRepository;
import ra.services.bill.IBillService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/bill",produces = "application/json")
public class BillApi {
    @Autowired
    private IBillService billService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IBillRepository billRepository;

    @PostMapping("/create-new-bills")
    public ResponseEntity<String> createNewBill(@RequestBody BookingRequestDTO bookingRequestDTO){

        try {
            billService.createNewBill(bookingRequestDTO);
        } catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return  new ResponseEntity<>("Bạn đã mua vé xem phim thành công !",HttpStatus.OK);
    }
    @GetMapping("/showBill")
    public List<BillDTO> showBill() {

        return billRepository.findAll()
                .stream().map(bill -> modelMapper.map(bill, BillDTO.class))
                .collect(Collectors.toList());
    }
}
