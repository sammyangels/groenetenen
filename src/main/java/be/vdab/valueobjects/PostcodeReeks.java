package be.vdab.valueobjects;

import be.vdab.constraints.Postcode;

import javax.validation.constraints.NotNull;

public class PostcodeReeks {
    @NotNull @Postcode
    private Integer vanpostcode;
    @NotNull @Postcode
    private Integer totpostcode;

    public Integer getVanpostcode() {
        return vanpostcode;
    }

    public void setVanpostcode(Integer vanpostcode) {
        this.vanpostcode = vanpostcode;
    }

    public Integer getTotpostcode() {
        return totpostcode;
    }

    public void setTotpostcode(Integer totpostcode) {
        this.totpostcode = totpostcode;
    }

    public boolean bevat(Integer postcode) {
        return postcode >= vanpostcode && postcode <= totpostcode;

    }

}
