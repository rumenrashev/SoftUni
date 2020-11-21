package productshop.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <O> O fromXml(Class<O> objectClass , String filePath) throws JAXBException;

    <O> void toXml(Class<O> objectClass, O object ,  String filePath) throws JAXBException;

}
