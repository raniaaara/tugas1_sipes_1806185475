package apap.tugas.sipes.service;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@Component("pesawatServiceImpl")
public class PesawatServiceImpl implements PesawatService {

    @Autowired
    PesawatDb pesawatDb;

    @Override
    public void addPesawat(PesawatModel pesawat){
        String no_seri = generateNoSeri(pesawat);
        pesawat.setNomor_seri(no_seri);
        pesawatDb.save(pesawat);
    }

    @Override
    public List<PesawatModel> getListPesawat(){
        return pesawatDb.findAll();
    }

    @Override
    public PesawatModel getPesawatById(Long id) {
        return pesawatDb.findById(id).get();
    }

    @Override
    public PesawatModel updatePesawat(PesawatModel pesawat) {

        PesawatModel targetPesawat = pesawatDb.findById(pesawat.getId()).get();

//        try{
            targetPesawat.setMaskapai(pesawat.getMaskapai());
            targetPesawat.setTanggal_dibuat(pesawat.getTanggal_dibuat());
            targetPesawat.setTempat_dibuat(pesawat.getTempat_dibuat());
            targetPesawat.setJenis_pesawat(pesawat.getJenis_pesawat());

//            String no_seri = generateNoSeri(pesawat);
//            pesawat.setNomor_seri(no_seri);

            pesawatDb.save(targetPesawat);
            return targetPesawat;

//        }catch (NullPointerException nullException){
//            return null;
//        }
    }

    @Override
    public String generateNoSeri(PesawatModel pesawat){
        String no_seri = "";

        // kode jenis pesawat
        if (pesawat.getJenis_pesawat().equals("Komersial")) no_seri += "1";
        if (pesawat.getJenis_pesawat().equals("Militer")) no_seri += "2";

        // kode tipe pesawat
        if(pesawat.getTipe().getNama().equals("Boeing")) no_seri += "BO";
        if(pesawat.getTipe().getNama().equals("ATR")) no_seri += "AT";
        if(pesawat.getTipe().getNama().equals("Airbus")) no_seri += "AB";
        if(pesawat.getTipe().getNama().equals("Bombardier")) no_seri += "BB";

        // kode tahun pembuatan pesawat dibalik
        int tahun = pesawat.getTanggal_dibuat().getYear();
        String tahunStr = Integer.toString(tahun);
        StringBuilder sb = new StringBuilder(tahunStr);
        String tahunReversed = sb.reverse().toString();
        no_seri += tahunReversed;

        // kode tahun pembuatan pesawat + 8
        tahun += 8;
        tahunStr = Integer.toString(tahun);
        no_seri += tahunStr;

        // huruf kapital random
        Random random = new Random();
        char char1 = (char)(random.nextInt(26) + 'a');
        char char2 = (char)(random.nextInt(26) + 'a');
        String strChar1 = String.valueOf(char1).toUpperCase();
        String strChar2 = String.valueOf(char2).toUpperCase();
        no_seri += strChar1;
        no_seri += strChar2;

        return no_seri;
    }
}
