package be.vdab.services;

import be.vdab.dao.WerknemerDAO;
import be.vdab.entities.Werknemer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

@ReadOnlyTransactionalService
class WerknemerServiceImpl implements WerknemerService {
    private final WerknemerDAO werknemerDAO;

    @Autowired
    WerknemerServiceImpl(WerknemerDAO werknemerDAO) {
        this.werknemerDAO = werknemerDAO;
    }

    @Override
    public List<Werknemer> findAll() {
        return werknemerDAO.findAll(new Sort("familienaam", "voornaam"));
    }
}
