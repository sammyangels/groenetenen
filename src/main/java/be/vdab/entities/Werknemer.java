package be.vdab.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "werknemers")
public class Werknemer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    private String voornaam;
    private String familienaam;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "filiaalId")
    private Filiaal filiaal;
    private BigDecimal wedde;
    private long rijksregisterNr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Werknemer werknemer = (Werknemer) o;

        return rijksregisterNr == werknemer.rijksregisterNr;

    }

    @Override
    public int hashCode() {
        return (int) (rijksregisterNr ^ (rijksregisterNr >>> 32));
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public Filiaal getFiliaal() {
        return filiaal;
    }

    public void setFiliaal(Filiaal filiaal) {
        this.filiaal = filiaal;
    }

    public BigDecimal getWedde() {
        return wedde;
    }

    public void setWedde(BigDecimal wedde) {
        this.wedde = wedde;
    }

    public long getRijksregisterNr() {
        return rijksregisterNr;
    }

    public void setRijksregisterNr(long rijksregisterNr) {
        this.rijksregisterNr = rijksregisterNr;
    }
}
