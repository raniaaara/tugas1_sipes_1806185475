package apap.tugas.sipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import apap.tugas.sipes.model.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="teknisi")
public class TeknisiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "no_telepon", nullable = false)
    private String no_telepon;

    //Relationship
    @ManyToMany(mappedBy = "listTeknisi")
    private List<PesawatModel> listPesawat;

//    @OneToMany(mappedBy = "teknisi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<PesawatTeknisiModel> listPesawatTeknisi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

//
//    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
//        return listPesawatTeknisi;
//    }
//
//    public void setListPesawatTeknisi(List<PesawatTeknisiModel> listPesawatTeknisi) {
//        this.listPesawatTeknisi = listPesawatTeknisi;
//    }
}