package xmlprocessing;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an eBook. A Builder pattern is used for simple eBook creation using
 * method chaining.
 */
@XmlRootElement(name="ebook")
public class Ebook {
    private String isbn;
    private String title;
    private List<String> authors;
    private List<Devices> devices;
    private double price;
    
    // this enumeration holds sample devices that could read an ebook
    public enum Devices {
        TABLET,
        KINDLE,
        SMARTPHONE;
        
        /**
         * Overridden for pretty printing (first later capitalized, the rest in
         * small caps).
         * @return a friendlier enum name
         */
        @Override
        public String toString() {
            String name = name();
            
            return name.charAt(0) + name.substring(1).toLowerCase();
        }
    }
    
    public static class Builder {
        private String isbn;
        private String title;
        private List<String> authors;
        private List<Devices> devices;
        private double price;
        
        public Builder() {
            isbn = "";
            title = "";
            authors = new ArrayList<>();
            devices = new ArrayList<>();
            price = 0d;
        }
        
        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }
        
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        
        public Builder setAuthors(List<String> authors) {
            this.authors.addAll(authors);
            return this;
        }
        
        public Builder setDevices(List<Devices> devices) {
            this.devices.addAll(devices);
            return this;
        }
        
        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }
        
        public Ebook build() {
            return new Ebook(this);
        }
    }
    
    // a constructor without parameters is needed for JAXB
    private Ebook() {}
    
    private Ebook(Builder builder) {
        isbn = builder.isbn;
        title = builder.title;
        authors = builder.authors;
        devices = builder.devices;
        price = builder.price;
    }
    
    /*
    The following methods are getters for Ebook properties, with relevant
    JAXB annotations.
    */
    
    @XmlElement(name="isbn")
    public String getIsbn() {
        return isbn;
    }
    
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    
    @XmlElementWrapper(name="authors")
    @XmlElement(name="author")
    public List<String> getAuthors() {
        return authors;
    }
    
    @XmlElementWrapper(name="devices")
    @XmlElement(name="device")
    public List<String> getDevices() {
        // here we return a list of friendly device names
        List<String> results = new ArrayList<>();
        
        for (Devices d : devices)
            results.add(d.toString());
        
        return results;
    }
    
    @XmlElement(name="price")
    public double getPrice() {
        return price;
    }
}