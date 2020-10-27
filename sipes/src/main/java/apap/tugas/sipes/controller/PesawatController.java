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

//    @Qualifier("pesawatTeknisiServiceImpl")
//    @Autowired
//    private PesawatTeknisiService pesawatTeknisiService;


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

    @GetMapping("/pesawat/add")
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

    @PostMapping("/pesawat/add")
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

        System.out.println(pesawat.getListTeknisi().size());

        for(TeknisiModel teknisi: pesawat.getListTeknisi()){
            teknisi = teknisiService.getTeknisiById(teknisi.getId());
        }

        pesawatService.addPesawat(pesawat);
        model.addAttribute("pesawat", pesawat);

        return "add-pesawat";
    }

    @PostMapping(value="/pesawat/add", params={"addteknisi"})
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

    @GetMapping("/pesawat/view/{id}")
    public String viewPesawat(
            @PathVariable Long id,
            Model model
    ){
            PesawatModel pesawat = pesawatService.getPesawatById(id);
            List<TeknisiModel> listTeknisi = pesawat.getListTeknisi();

            model.addAttribute("pesawat", pesawat);
            model.addAttribute("listTeknisi", listTeknisi);

            return "view-pesawat";
    }


    @GetMapping("/pesawat/update/{id}")
    public String update_pesawat_form(
            @PathVariable Long id,
            Model model
    ){
//        try{
            PesawatModel pesawat = pesawatService.getPesawatById(id);
            model.addAttribute("pesawat", pesawat);
            return "form-update-pesawat";

//        }catch(Exception e){
//            model.addAttribute("id", id);
//            return "error";
//        }
    }

    @PostMapping("/pesawat/update")
    public String update_pesawat_submit(
            @ModelAttribute PesawatModel pesawat,
            Model model
    ){
        pesawat.setTipe(tipeService.getTipeById(pesawat.getTipe().getId()));

        String no_seri = pesawatService.generateNoSeri(pesawat);
        pesawat.setNomor_seri(no_seri);

        PesawatModel pesawatUpdated = pesawatService.updatePesawat(pesawat);
        model.addAttribute("pesawat", pesawatUpdated);
        // model.addAttribute("pesawat", pesawat);
        return "update-pesawat";
    }
}