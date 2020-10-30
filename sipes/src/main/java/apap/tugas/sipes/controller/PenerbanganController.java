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
public class PenerbanganController{
    @Qualifier("penerbanganServiceImpl")
    @Autowired
    private PenerbanganService penerbanganService;

    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Qualifier("teknisiServiceImpl")
    @Autowired
    private TeknisiService teknisiService;

    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

    @GetMapping("/penerbangan")
    public String daftar_penerbangan(Model model){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getListPenerbangan();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "daftar-penerbangan";
    }

    @GetMapping("/penerbangan/tambah")
    public String add_penerbangan_form(Model model){
        PenerbanganModel penerbangan = new PenerbanganModel();
        model.addAttribute("penerbangan", penerbangan);
        model.addAttribute("message", "");

        return "form-add-penerbangan";
    }


    @PostMapping("/penerbangan/tambah")
    public String submitTambahPenerbangan(
            @ModelAttribute PenerbanganModel penerbangan, Model model
    ){
        if (penerbangan.getWaktu_berangkat() == null){
            penerbangan.setWaktu_berangkat(LocalDateTime.now());
        }
//        Boolean checkNo = penerbanganService.getListNomorPenerbangan().contains(penerbangan.getNomor_penerbangan());
//        if((penerbangan.getNomor_penerbangan().length() != 16) || checkNo) {
//            model.addAttribute("msg", "Nomor penerbangan harus berjumlah 16 digit dan unik");
//            return "form-add-penerbangan";
//        }

        penerbanganService.addPenerbangan(penerbangan);

        model.addAttribute("msg", "");
        model.addAttribute("penerbangan", penerbangan);

        return "add-penerbangan";
    }

    @GetMapping("/penerbangan/{id}")
    public String view_penerbangan(
            @PathVariable Long id,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id);
        model.addAttribute("penerbangan", penerbangan);
        return "view-penerbangan";
    }

    @GetMapping("/penerbangan/ubah/{id}")
    public String update_penerbangan_form(
            @PathVariable Long id,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id);
        model.addAttribute("penerbangan", penerbangan);
        return "form-update-penerbangan";
    }

    @PostMapping("penerbangan/ubah")
    public String update_penerbangan_submit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model
    ){
        PenerbanganModel penerbanganUpdated = penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("penerbanganUpdated", penerbanganUpdated);
        return "update-penerbangan";
    }

//    @GetMapping("/penerbangan/hapus/{id}")
//    public String hapusPenerbangan(
//            @PathVariable(value="id") Long id, Model model
//    ){
//        PenerbanganModel p = penerbanganService.getPenerbanganById(id);
//        penerbanganService.hapusPenerbangan(p);
//        model.addAttribute("penerbangan", p);
//        return listPenerbangan(model);
//    }
}