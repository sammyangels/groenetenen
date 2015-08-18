package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
@RequestMapping("/offertes")
class OfferteController {
    private static final String STAP1_VIEW = "offertes/stap1";
    private static final String STAP2_VIEW = "offertes/stap2";
    private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/";
    private static final Logger LOGGER = Logger.getLogger(OfferteController.class.getName());

    @RequestMapping(value = "aanvraag", method = RequestMethod.GET)
    String createForm1() {
        return STAP1_VIEW;
    }
    @RequestMapping(method = RequestMethod.POST, params = "van1naar2")
    String createForm1naar2() {
        return STAP2_VIEW;
    }
    @RequestMapping(method = RequestMethod.POST, params = "van2naar1")
    String createForm2naar1() {
        return STAP1_VIEW;
    }
    @RequestMapping(method = RequestMethod.POST, params = "bevestigen")
    String create() {
        LOGGER.info("offerte versturen via e-mail");
        return REDIRECT_URL_NA_TOEVOEGEN;
    }
}
