package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface PesawatTeknisiDb extends JpaRepository<PesawatTeknisiModel,Long>{
    Optional<PesawatTeknisiModel> findById(Long id);
}
