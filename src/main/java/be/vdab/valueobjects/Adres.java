package be.vdab.valueobjects;

import be.vdab.constraints.Postcode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Adres implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank
    @Length(min = 1, max = 50)
    private String straat;
    @NotBlank
    @Length(min = 1, max = 7)
    private String huisNr;
    @NotNull
    @Postcode
    private Integer postcode;
    @NotBlank
    @Length
    private String gemeente;

    public Adres() {}

    public Adres(String straat, String huisNr, Integer postcode, String gemeente) {
        this.straat = straat;
        this.huisNr = huisNr;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
