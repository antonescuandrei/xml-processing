package xmlprocessing;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A simple container of ebooks.
 */
@XmlRootElement(name="stock")
public class Stock {
    @XmlElementWrapper(name="ebooks")
    @XmlElement(name="ebook")
    private List<Ebook> ebooks;
    
    @XmlAttribute
    private String name;
    
    // constructor without parameters is needed for JAXB
    private Stock() {}
    
    public Stock(String name) {
        this.ebooks = new ArrayList<>();
        this.name = name;
    }
    
    /**
     * Adds an ebook to the stock.
     * @param ebook the ebook to be added
     */
    public void addEbook(Ebook ebook) {
        ebooks.add(ebook);
    }
    
    public List<Ebook> getStock() {
        return ebooks;
    }
}