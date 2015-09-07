package be.vdab.services;

import be.vdab.entities.Werknemer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WerknemerService {
    Page<Werknemer> findAll(Pageable pageable);
}
