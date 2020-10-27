package apap.tugas.sipes.service;

import java.util.List;
import apap.tugas.sipes.model.*;
import org.springframework.stereotype.Service;

@Service
public interface PenerbanganService {
    void addPenerbangan(PenerbanganModel penerbangan);
}
