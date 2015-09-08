package be.vdab.dao;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.PostcodeReeks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface FiliaalDAO extends JpaRepository<Filiaal, Long> {
    List<Filiaal> findByAdresPostcodeBetweenOrderByNaamAsc(int van, int tot);

    List<Filiaal> findByWaardeGebouwNot(BigDecimal waarde);
}
