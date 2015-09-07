package be.vdab.dao;

import be.vdab.entities.Werknemer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WerknemerDAO extends JpaRepository<Werknemer, Long> {
    @Override
    @EntityGraph("Werknemer.metFiliaal")
    List<Werknemer> findAll(Sort sort);
}
