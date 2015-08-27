package be.vdab.valueobjects;

import be.vdab.constraints.Postcode;
import be.vdab.constraints.PostcodeReeksVanKleinerDanOfGelijkAanTot;

import javax.validation.constraints.NotNull;

@PostcodeReeksVanKleinerDanOfGelijkAanTot
public class PostcodeReeks {
    @NotNull @Postcode
    private Integer vanpostcode;
    @NotNull @Postcode
    private Integer totpostcode;

    public Integer getVanpostcode() {
        return vanpostcode;
    }

    public Integer getTotpostcode() {
        return totpostcode;
    }

    public boolean bevat(Integer postcode) {
        return postcode >= vanpostcode && postcode <= totpostcode;

    }

}
