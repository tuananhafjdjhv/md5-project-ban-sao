package ra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dtos.BranchDTO;
import ra.services.branch.IBranchService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/api/branches", produces = "application/json")
public class BranchApi {
    @Autowired
    private IBranchService branchService;
    @GetMapping("/branch/{movieId}")
   public ResponseEntity<List<BranchDTO>> getBranchesThatShowTheMovie(@PathVariable Integer movieId){
        return new ResponseEntity<>(branchService.getBranchesThatShowTheMovie(movieId), HttpStatus.OK);
    }
}
