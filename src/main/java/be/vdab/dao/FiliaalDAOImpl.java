package be.vdab.dao;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.PostcodeReeks;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class FiliaalDAOImpl implements FiliaalDAO {
    private final Map<Long, Filiaal> filialen = new ConcurrentHashMap<>();
    FiliaalDAOImpl() {
        filialen.put(1L, new Filiaal(1, "Andros", true, BigDecimal.valueOf(1000),
                new Date(), new Adres("Keizerslaan", "11", 1000, "Brussel")));
        filialen.put(2L, new Filiaal(2, "Delos", false, BigDecimal.valueOf(2000),
                new Date(), new Adres("Gasthuisstraat", "31", 1000, "Brussel")));
        filialen.put(3L, new Filiaal(3, "Gavdos", false, BigDecimal.valueOf(3000),
                new Date(), new Adres("Koestraat", "44", 9700, "Oudenaarde")));
    }


    @Override
    public void create(Filiaal filiaal) {
        filiaal.setId(Collections.max(filialen.keySet()) + 1);
        filialen.put(filiaal.getId(), filiaal);
    }

    @Override
    public Filiaal read(long id) {
        return filialen.get(id);
    }

    @Override
    public void update(Filiaal filiaal) {
        filialen.put(filiaal.getId(), filiaal);
    }

    @Override
    public void delete(long id) {
        filialen.remove(id);
    }

    @Override
    public List<Filiaal> findAll() {
        return new ArrayList<>(filialen.values());
    }

    @Override
    public long findAantalFilialen() {
        return filialen.size();
    }

    @Override
    public long findAantalWerknemers(long id) {
        return id == 1L ? 7L : 0L;
    }

    @Override
    public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
        List<Filiaal> filialen = new ArrayList<>();
        for (Filiaal filiaal : this.filialen.values()) {
            if (reeks.bevat(filiaal.getAdres().getPostcode())) {
                filialen.add(filiaal);
            }
        }
        return filialen;
    }
}
