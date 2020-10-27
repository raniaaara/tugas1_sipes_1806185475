//package apap.tugas.sipes.service;
//
//import apap.tugas.sipes.model.*;
//
//import apap.tugas.sipes.repository.PesawatTeknisiDb;
//import apap.tugas.sipes.repository.TeknisiDb;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Service
//@Transactional
//@Component("pesawatTeknisiServiceImpl")
//public class PesawatTeknisiServiceImpl implements PesawatTeknisiService {
//
//    @Autowired
//    PesawatTeknisiDb pesawatTeknisiDb;
//
//    @Override
//    public void addPesawatTeknisi(PesawatTeknisiModel pesawatTeknisi){
//        pesawatTeknisiDb.save(pesawatTeknisi);
//    }
//
//    @Override
//    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
//        return pesawatTeknisiDb.findAll();
//    }
//}
