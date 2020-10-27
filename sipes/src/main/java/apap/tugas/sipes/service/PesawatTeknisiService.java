package apap.tugas.sipes.service;

import apap.tugas.sipes.model.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PesawatTeknisiService {
    void addPesawatTeknisi(PesawatTeknisiModel pesawatTeknisi);
    List<PesawatTeknisiModel> getListPesawatTeknisi();
}