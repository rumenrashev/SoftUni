package productshop.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import java.io.File;

public class XmlParserImpl implements XmlParser {


    @Override
    @SuppressWarnings("unchecked casting")
    public <O> O fromXml(Class<O> objectClass, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (O) unmarshaller.unmarshal(new File(filePath));
    }

    @Override
    public <O> void toXml(Class<O> objectClass, O object, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders",
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        marshaller.marshal(object,new File(filePath));
    }
}
