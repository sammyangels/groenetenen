package be.vdab.web;

import be.vdab.entities.Filiaal;

import javax.validation.constraints.NotNull;
import java.util.List;


class AfschrijvenForm {
    public List<Filiaal> getFilialen() {
        return filialen;
    }

    public void setFilialen(List<Filiaal> filialen) {
        this.filialen = filialen;
    }

    @NotNull
    private List<Filiaal> filialen;


}
