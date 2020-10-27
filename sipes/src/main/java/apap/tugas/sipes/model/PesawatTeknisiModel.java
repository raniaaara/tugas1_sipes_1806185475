//package apap.tugas.sipes.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//import org.springframework.format.annotation.DateTimeFormat;
//import apap.tugas.sipes.model.*;
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name="pesawatTeknisi")
//public class PesawatTeknisiModel implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "id_pesawat", referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private PesawatModel pesawat;
//
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "id_teknisi", referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private TeknisiModel teknisi;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public PesawatModel getPesawat() {
//        return pesawat;
//    }
//
//    public void setPesawat(PesawatModel pesawat) {
//        this.pesawat = pesawat;
//    }
//
//    public TeknisiModel getTeknisi() {
//        return teknisi;
//    }
//
//    public void setTeknisi(TeknisiModel teknisi) {
//        this.teknisi = teknisi;
//    }
//}
