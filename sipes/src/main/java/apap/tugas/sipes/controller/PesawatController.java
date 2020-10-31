package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.boot.web.servlet.error.ErrorController;


@Controller
public class PesawatController{

    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Qualifier("teknisiServiceImpl")
    @Autowired
    private TeknisiService teknisiService;

    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

    @Qualifier("penerbanganServiceImpl")
    @Autowired
    private PenerbanganService penerbanganService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/pesawat")
    public String daftar_pesawat(Model model){
        List<PesawatModel> listPesawat = pesawatService.getListPesawat();
        model.addAttribute("listPesawat", listPesawat);
        return "daftar-pesawat";
    }

    @GetMapping("/pesawat/tambah")
    public String add_pesawat_form(Model model){
        PesawatModel pesawat = new PesawatModel();
        model.addAttribute("pesawat", pesawat);

        List<TipeModel> listTipe = tipeService.getListTipe();
        List<TeknisiModel> allTeknisi = teknisiService.getListTeknisi();
        List<TeknisiModel> listTeknisi = new ArrayList<TeknisiModel>();

        listTeknisi.add(new TeknisiModel());
        pesawat.setListTeknisi(listTeknisi);

        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listTeknisi", listTeknisi);
        model.addAttribute("allTeknisi", allTeknisi);

        int jumlahtipe = listTipe.size();
        model.addAttribute("jumlahtipe", jumlahtipe);

        return "form-add-pesawat";
    }

    @PostMapping("/pesawat/tambah")
    public String add_pesawat_submit(
            @ModelAttribute PesawatModel pesawat,
            Model model
    ){
        if (pesawat.getTanggal_dibuat() == null) {
            pesawat.setTanggal_dibuat(LocalDate.now());
        }

        pesawat.setTipe(tipeService.getTipeById(pesawat.getTipe().getId()));

        String no_seri = pesawatService.generateNoSeri(pesawat);
        pesawat.setNomor_seri(no_seri);

        for(TeknisiModel teknisi: pesawat.getListTeknisi()){
            teknisi = teknisiService.getTeknisiById(teknisi.getId());
        }

        pesawatService.addPesawat(pesawat);
        model.addAttribute("pesawat", pesawat);

        return "add-pesawat";
    }

    @PostMapping(value="/pesawat/tambah", params={"addTeknisi"})
    public String add_pesawat_add_teknisi(
            @ModelAttribute PesawatModel pesawat,
            Model model
    ){
        List<TeknisiModel> allTeknisi = teknisiService.getListTeknisi();
        pesawat.getListTeknisi().add(new TeknisiModel());
        model.addAttribute("pesawat", pesawat);

        List<TipeModel> listTipe = tipeService.getListTipe();
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("allTeknisi", allTeknisi);

        return "form-add-pesawat";
    }

    @GetMapping("/pesawat/{id}")
    public String view_pesawat(
            @PathVariable Long id,
            Model model
    ){
            PesawatModel pesawat = pesawatService.getPesawatById(id);
            List<TeknisiModel> listTeknisi = pesawat.getListTeknisi();
            List<PenerbanganModel> dropdownPenerbangan = penerbanganService.getListPenerbangan();
            List<PenerbanganModel> listPenerbangan = pesawat.getListPenerbangan();

            model.addAttribute("pesawat", pesawat);
            model.addAttribute("listTeknisi", listTeknisi);
            model.addAttribute("dropdownPenerbangan", dropdownPenerbangan);
            model.addAttribute("listPenerbangan", listPenerbangan);

            return "view-pesawat";
    }

    @PostMapping("/pesawat/{id}/tambah-penerbangan")
    public String view_pesawat_assign_penerbangan(
            @PathVariable(value = "id") Long id, @RequestParam(value="penerbanganId") Long penerbanganId,
            Model model
    ){
        PesawatModel pesawat = pesawatService.getPesawatById(id);
        List<TeknisiModel> listTeknisi = pesawat.getListTeknisi();
        List<PenerbanganModel> dropdownPenerbangan = penerbanganService.getListPenerbangan();

        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(penerbanganId);
        System.out.println(penerbangan.getNomor_penerbangan());
        penerbangan.setPesawat(pesawat);
        List<PenerbanganModel> listPenerbangan = pesawat.getListPenerbangan();
        System.out.println(penerbangan.getPesawat().getNomor_seri());
        System.out.println(listPenerbangan.size());
//        penerbangan.getPesawat().getMaskapai();

        model.addAttribute("listTeknisi", listTeknisi);
        model.addAttribute("dropdownPenerbangan", dropdownPenerbangan);
        model.addAttribute("listPenerbangan",listPenerbangan );
        model.addAttribute("pesawat", pesawat);

        return "view-pesawat";
    }

    @GetMapping("/pesawat/ubah/{id}")
    public String update_pesawat_form(
            @PathVariable Long id,
            Model model
    ){
            PesawatModel pesawat = pesawatService.getPesawatById(id);
            model.addAttribute("pesawat", pesawat);
            return "form-update-pesawat";
    }

    @PostMapping("/pesawat/ubah")
    public String update_pesawat_submit(
            @ModelAttribute PesawatModel pesawat,
            Model model
    ) {
        pesawat.setTipe(tipeService.getTipeById(pesawat.getTipe().getId()));
        String no_seri = pesawatService.generateNoSeri(pesawat);
        pesawat.setNomor_seri(no_seri);

        PesawatModel pesawatUpdated = pesawatService.updatePesawat(pesawat);
        model.addAttribute("pesawatUpdated", pesawatUpdated);
        return "update-pesawat";
    }

    @GetMapping("/pesawat/pesawat-tua")
    private String daftar_pesawat_tua(
            Model model
    ){
        List<PesawatModel> listPesawat = pesawatService.getListPesawat();
        List<PesawatModel> listPesawatTua = new ArrayList<PesawatModel>();

        for (PesawatModel pesawat : listPesawat) {
            Period period = Period.between(pesawat.getTanggal_dibuat(), LocalDate.now());
            Integer usia = period.getYears();
            pesawat.setUsia(Integer.toString(usia));
            if (usia >= 10){
                listPesawatTua.add(pesawat);
            }
        }
        model.addAttribute("listPesawat", listPesawatTua);
        return "daftar-pesawat-tua";
    }

    @GetMapping("/pesawat/filter")
    public String filterPesawat(
            @RequestParam(defaultValue = "0", name="idPenerbangan") Long idPenerbangan,
            @RequestParam(defaultValue = "0", name="idTipe") Long idTipe,
            @RequestParam(defaultValue = "0", name="idTeknisi") Long idTeknisi,
            Model model
    ){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getListPenerbangan();
        List<TipeModel> listTipe = tipeService.getListTipe();
        List<TeknisiModel> listTeknisi = teknisiService.getListTeknisi();
        Boolean containsObject = false;

        model.addAttribute("listPenerbangan", listPenerbangan);
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listTeknisi", listTeknisi);
        model.addAttribute("containsObject", containsObject);

        Boolean check = ((idPenerbangan != 0) || (idTipe != 0) || (idTeknisi != 0));
        if(check){
            List<PesawatModel> listPesawat = pesawatService.getListPesawat();
            List<PesawatModel> listPesawatHasil = new ArrayList<PesawatModel>();
            for(PesawatModel pesawat : listPesawat){ listPesawatHasil.add(pesawat); }

            for(PesawatModel pesawat : listPesawat){
                if(idPenerbangan != 0){
                    PenerbanganModel filterPenerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
                    List<PenerbanganModel> penerbangan = pesawat.getListPenerbangan();
                    if (!penerbangan.contains(filterPenerbangan)){
                        listPesawatHasil.remove(pesawat);
                    }
                }
                if(idTipe != 0){
                    TipeModel filterTipe = tipeService.getTipeById(idTipe);
                    TipeModel tipe = pesawat.getTipe();
                    if(tipe.getId() != filterTipe.getId()){
                        listPesawatHasil.remove(pesawat);
                    }
                }
                if(idTeknisi != 0){
                    TeknisiModel filterTeknisi = teknisiService.getTeknisiById(idTeknisi);
                    List<TeknisiModel> teknisi = pesawat.getListTeknisi();
                    if(!teknisi.contains(filterTeknisi)){
                        listPesawatHasil.remove(pesawat);
                    }
                }
            }
            if(listPesawatHasil.size() != 0) containsObject = true;
            model.addAttribute("containsObject", containsObject);
            model.addAttribute("listPesawatHasil", listPesawatHasil);
        }
        return "filter-pesawat";
    }

}