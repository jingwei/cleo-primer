package cleo.primer.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "element-list")
public class ElementListDTO {
    
    @XmlElement(name = "element")
    public List<ElementDTO> elements;
    
    public ElementListDTO() {
        this.elements = new ArrayList<ElementDTO>();
    }
    
    public ElementListDTO(List<ElementDTO> elements) {
        this.elements = elements;
    }
}
