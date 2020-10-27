package apap.tugas.sipes.service;

import java.util.List;
import apap.tugas.sipes.model.*;
import org.springframework.stereotype.Service;

@Service
public interface PesawatService {
    void addPesawat(PesawatModel pesawat);
    List<PesawatModel>getListPesawat();
    PesawatModel getPesawatById(Long id);
    PesawatModel updatePesawat(PesawatModel pesawat);
    String generateNoSeri(PesawatModel pesawat);
}
