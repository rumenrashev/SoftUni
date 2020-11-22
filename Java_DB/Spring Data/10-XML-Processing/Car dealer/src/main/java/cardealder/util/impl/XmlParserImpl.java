package cardealder.util.impl;

import cardealder.util.api.XmlParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
@SuppressWarnings("unchecked casting")
public class XmlParserImpl implements XmlParser {
    @Override
    public <O> O fromXML(Class<O> objectClass, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (O) unmarshaller.unmarshal(new File(filePath));
    }

    @Override
    public <O> void toXML(Class<O> objectClass, O object, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders",
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        marshaller.setAdapter(new LocalDateTimeXmlAdapter());
        marshaller.marshal(object, new File(filePath));
    }
}
