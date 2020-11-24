package softuni.exam.util.impl;

import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class XmlParserImpl implements XmlParser {
    @Override
    public <T> T fromXmlDocument(Class<T> objectClass, String filePath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new File(filePath));
    }
}
