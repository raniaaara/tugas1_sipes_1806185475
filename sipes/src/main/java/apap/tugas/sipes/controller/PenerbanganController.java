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


}
//    @GetMapping("/penerbangan/daftar_penerbangan")
//    private String daftar_pesawat(Model model){
//        List<PenerbanganModel> listPenerbangan = penerbanganService.getListPenerbangan();
//        model.addAttribute("listPenerbangan", listPenerbangan);
//        return "daftar-penerbangan";
//    }