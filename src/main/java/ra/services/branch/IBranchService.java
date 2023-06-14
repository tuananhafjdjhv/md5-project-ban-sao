package ra.services.branch;

import ra.dtos.BranchDTO;

import java.util.List;

public interface IBranchService {
    List<BranchDTO> getBranchesThatShowTheMovie(Integer movieId);
}
