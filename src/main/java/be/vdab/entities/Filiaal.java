package be.vdab.entities;

import be.vdab.valueobjects.Adres;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "filialen")
public class Filiaal implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    @Length(min = 1, max = 50)
    @SafeHtml
    private String naam;
    private boolean hoofdFiliaal;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @NotNull
    @Min(0)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal waardeGebouw;
    @DateTimeFormat(style = "S-")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date inGebruikName;
    @Valid
    @Embedded
    private Adres adres;
    @OneToMany(mappedBy = "filiaal")
    private Set<Werknemer> werknemers;
    public Set<Werknemer> getWerknemers() {
        return Collections.unmodifiableSet(werknemers);
    }

    public Filiaal() {}

    public Filiaal(String naam,
                   boolean hoofdFiliaal,
                   BigDecimal waardeGebouw,
                   Date inGebruikName, Adres adres) {
        this.naam = naam;
        this.hoofdFiliaal = hoofdFiliaal;
        this.waardeGebouw = waardeGebouw;
        this.inGebruikName = inGebruikName;
        this.adres = adres;
    }

    public Filiaal(long id, String naam, boolean hoofdFiliaal, BigDecimal waardeGebouw, Date inGebruikName, Adres adres) {
        this(naam, hoofdFiliaal, waardeGebouw, inGebruikName, adres);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public boolean isHoofdFiliaal() {
        return hoofdFiliaal;
    }

    public void setHoofdFiliaal(boolean hoofdFiliaal) {
        this.hoofdFiliaal = hoofdFiliaal;
    }

    public BigDecimal getWaardeGebouw() {
        return waardeGebouw;
    }

    public void setWaardeGebouw(BigDecimal waardeGebouw) {
        this.waardeGebouw = waardeGebouw;
    }

    public Date getInGebruikName() {
        return inGebruikName;
    }

    public void setInGebruikName(Date inGebruikName) {
        this.inGebruikName = inGebruikName;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
}
