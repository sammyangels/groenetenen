package be.vdab.services;

import be.vdab.dao.FiliaalDAO;
import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.valueobjects.PostcodeReeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ReadOnlyTransactionalService
class FiliaalServiceImpl implements FiliaalService {
    private final FiliaalDAO filiaalDAO;

    @Autowired
    FiliaalServiceImpl(FiliaalDAO filiaalDAO) {
        this.filiaalDAO = filiaalDAO;
    }

    @Override
    @ModifyingTransactionalServiceMethod
    public void create(Filiaal filiaal) {
        filiaalDAO.create(filiaal);
    }

    @Override
    public Filiaal read(long id) {
        return filiaalDAO.read(id);
    }

    @Override
    @ModifyingTransactionalServiceMethod
    public void update(Filiaal filiaal) {
        filiaalDAO.update(filiaal);
    }

    @Override
    @ModifyingTransactionalServiceMethod
    public void delete(long id) {
        Filiaal filiaal = filiaalDAO.read(id);
        if (filiaal != null) {
            if (!filiaal.getWerknemers().isEmpty()) {
                throw new FiliaalHeeftNogWerknemersException();
            }
            filiaalDAO.delete(id);
        }
    }

    @Override
    public List<Filiaal> findAll() {
        return filiaalDAO.findAll();
    }

    @Override
    public long findAantalFilialen() {
        return filiaalDAO.findAantalFilialen();
    }

    @Override
    public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
        return filiaalDAO.findByPostcodeReeks(reeks);
    }
}
