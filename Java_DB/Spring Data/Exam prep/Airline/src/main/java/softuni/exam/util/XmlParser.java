package softuni.exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <T> T fromXmlDocument(Class<T> object ,String filePath) throws JAXBException;

}
