package be.vdab.web;

import be.vdab.services.WerknemerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/werknemers")
class WerknemerController {
	private static final String WERKNEMERS_VIEW = "werknemers/werknemers";
    private final WerknemerService werknemerService;

    @Autowired
    WerknemerController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView findAll() {
		return  new ModelAndView(WERKNEMERS_VIEW, "werknemers", werknemerService.findAll());
	}
}
