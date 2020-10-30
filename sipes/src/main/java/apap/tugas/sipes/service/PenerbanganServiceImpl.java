package apap.tugas.sipes.service;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.repository.PenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService {

    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan){ penerbanganDb.save(penerbangan); }

    @Override
    public PenerbanganModel getPenerbanganById(Long id) {
        return penerbanganDb.findById(id).get();
    }

    @Override
    public List<PenerbanganModel> getListPenerbangan() {
        return penerbanganDb.findAll();
    }

    @Override
    public List<String> getListNomorPenerbangan() {
        List<PenerbanganModel> listPenerbangan = penerbanganDb.findAll();
        List<String> listNomor = new ArrayList<String>();
        for(PenerbanganModel a : listPenerbangan) listNomor.add(a.getNomor_penerbangan());
        return listNomor;
    }


    @Override
    public PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan) {
        PenerbanganModel targetPenerbangan = penerbanganDb.findById(penerbangan.getId()).get();
        targetPenerbangan.setKode_bandara_tujuan(penerbangan.getKode_bandara_tujuan());
        targetPenerbangan.setWaktu_berangkat(penerbangan.getWaktu_berangkat());
        targetPenerbangan.setNomor_penerbangan(penerbangan.getNomor_penerbangan());
        targetPenerbangan.setKode_bandara_asal(penerbangan.getKode_bandara_asal());
        penerbanganDb.save(targetPenerbangan);
        return targetPenerbangan;
    }

    @Override
    public void deletePenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.delete(penerbangan);
    }
}
