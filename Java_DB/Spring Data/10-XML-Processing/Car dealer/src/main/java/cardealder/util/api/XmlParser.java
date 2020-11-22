package cardealder.util.api;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <O> O fromXML(Class<O> objectClass , String filePath) throws JAXBException;

    <O> void toXML(Class<O> objectClass , O object, String filePath) throws JAXBException;

}
