package apap.tugas.sipes.service;

import java.util.List;
import apap.tugas.sipes.model.*;
import org.springframework.stereotype.Service;

@Service
public interface TeknisiService {
    void addTeknisi(TeknisiModel teknisi);
    List<TeknisiModel> getListTeknisi();
    TeknisiModel getTeknisiById(Long id);
}