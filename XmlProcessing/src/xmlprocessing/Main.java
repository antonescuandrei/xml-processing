package xmlprocessing;

import java.io.File;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Simple example used to demonstrate how to work with XML files in Java.
 * The JAXB API is used to create an XML file with information about eBooks.
 */
public class Main {
    // holds the name of the file where the XML output will be saved
    private final static String FILE_NAME = "stock.xml";
    // holds the name of the XSL file used to format the XML document
    private final static String XSL_NAME = "template.xsl";
    
    /**
     * Creates several sample Ebook instances and writes them to an XML file.
     * @param args command-line parameters (not used)
     */
    public static void main(String[] args) {
        // create a couple of Ebook instances
        Ebook dayworld = new Ebook
        .Builder()
            .setIsbn("973-9439-73-8")
            .setTitle("Dayworld")
            .setAuthors(Arrays.asList("Philip Jose Farmer"))
            .setDevices(Arrays.asList(Ebook.Devices.KINDLE))
            .setPrice(1.99)
        .build();
        
        Ebook efectulLazar = new Ebook
        .Builder()
            .setIsbn("973-569-806-4")
            .setTitle("Efectul Lazar")
            .setAuthors(Arrays.asList("Frank Herbert", "Bill Ransom"))
            .setDevices(Arrays.asList(Ebook.Devices.KINDLE))
            .setPrice(3.99)
        .build();
        
        Ebook lordulLuminii = new Ebook
        .Builder()
            .setIsbn("973-569-865-X")
            .setTitle("Lordul Luminii")
            .setAuthors(Arrays.asList("Roger Zelazny"))
            .setDevices(Arrays.asList(Ebook.Devices.KINDLE, Ebook.Devices.TABLET))
            .setPrice(2.99)
        .build();
        
        Ebook venissUnderground = new Ebook
        .Builder()
            .setIsbn("978-973-733-095-6")
            .setTitle("Veniss Underground")
            .setAuthors(Arrays.asList("Jeff Vandermeer"))
            .setDevices(Arrays.asList(Ebook.Devices.KINDLE))
            .setPrice(1.49)
        .build();
        
        Ebook poarta = new Ebook
        .Builder()
            .setIsbn("978-973-143-301-1")
            .setTitle("Poarta")
            .setAuthors(Arrays.asList("Frederik Pohl"))
            .setDevices(Arrays.asList(Ebook.Devices.KINDLE))
            .setPrice(2.49)
        .build();
        
        // create a Stock and add the ebooks to it
        Stock newStock = new Stock("eBookStore");
        newStock.addEbook(dayworld);
        newStock.addEbook(efectulLazar);
        newStock.addEbook(lordulLuminii);
        newStock.addEbook(venissUnderground);
        newStock.addEbook(poarta);
        
        // save data to an XML file using JAXB
        try {
            JAXBContext context = JAXBContext.newInstance(Stock.class);
            
            Marshaller jaxbMarshaller = context.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            // append a style sheet to the XML output for pretty formatting
            jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml-stylesheet type='text/xsl' href='" + XSL_NAME + "'?>");
            
            // uncomment the following line to print XML output to console
            // jaxbMarshaller.marshal(newStock, System.out);
            
            jaxbMarshaller.marshal(newStock, new File(FILE_NAME));
        } catch (JAXBException ex) {
            System.out.println(ex.toString());
        }
    }
}