package softuni.exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <T> T fromXML(Class<T> objectClass, String path) throws JAXBException;

    <T> void  toXML(T object, String path) throws JAXBException;

}
