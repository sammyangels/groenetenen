package be.vdab.web;

import be.vdab.entities.Filiaal;
import be.vdab.services.FiliaalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
@RequestMapping("/filialen")
class FiliaalController {
    private final FiliaalService filiaalService;
    private static final String FILIALEN_VIEW = "filialen/filialen";
    private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
    private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
    private static final String FILIAAL_VIEW = "filialen/filiaal";
    private static final Logger logger =
            Logger.getLogger(FiliaalController.class.getName());

    @Autowired
    FiliaalController(FiliaalService filiaalService) {
        this.filiaalService = filiaalService;
    }

    @RequestMapping(method = RequestMethod.GET, params = "id")
    ModelAndView read(long id) {
        ModelAndView modelAndView = new ModelAndView(FILIAAL_VIEW);
        Filiaal filiaal = filiaalService.read(id);
        if (filiaal != null) {
            modelAndView.addObject(filiaal);
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    String create() {
        logger.info("filiaal record toevoegen aan database");
        return REDIRECT_URL_NA_TOEVOEGEN;
    }

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView findAll() {
        return new ModelAndView(FILIALEN_VIEW, "filialen",filiaalService.findAll());
    }

    @RequestMapping(value = "toevoegen", method = RequestMethod.GET)
    String createForm() {
        return TOEVOEGEN_VIEW;
    }

}
