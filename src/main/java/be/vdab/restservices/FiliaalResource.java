package be.vdab.restservices;

import be.vdab.entities.Filiaal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD) @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class FiliaalResource extends ResourceSupport {
    @SuppressWarnings("unused")
    private Filiaal filiaal;

    public FiliaalResource() {}

    public FiliaalResource(Filiaal filiaal, EntityLinks entityLinks) {
        this.filiaal = filiaal;
        this.add(entityLinks.linkToSingleResource(Filiaal.class, filiaal.getId()));
        this.add(entityLinks.linkForSingleResource(Filiaal.class, filiaal.getId()).slash("werknemers").withRel("werknemers"));
    }
}
