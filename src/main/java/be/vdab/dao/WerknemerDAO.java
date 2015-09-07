package be.vdab.dao;

import be.vdab.entities.Werknemer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WerknemerDAO extends JpaRepository<Werknemer, Long> {}
