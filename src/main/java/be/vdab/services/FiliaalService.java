package be.vdab.services;

import be.vdab.entities.Filiaal;

import java.util.List;

public interface FiliaalService {
    void create(Filiaal filiaal);

    Filiaal read(long id);

    void update(Filiaal filiaal);

    void delete(long id);

    List<Filiaal> findAll();

    long findAantalFilialen();
}
