package apap.tugas.sipes.service;

import java.util.List;
import apap.tugas.sipes.model.*;
import org.springframework.stereotype.Service;

@Service
public interface PenerbanganService {
    void addPenerbangan(PenerbanganModel penerbangan);
    List<PenerbanganModel> getListPenerbangan();
    PenerbanganModel getPenerbanganById(Long id);
    List<String> getListNomorPenerbangan();
    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);
    void deletePenerbangan(PenerbanganModel penerbangan);
}
