package br.edu.ifpb.pos.entidade;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class MessageValidator {
    
    public static boolean validate(String message) {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("src/main/resources/message.xsd"));

            StringReader reader = new StringReader(message);

            JAXBContext jaxbContext = JAXBContext.newInstance(Mensagem.class);
            Unmarshaller marshaller = jaxbContext.createUnmarshaller();
            marshaller.setSchema(schema);

            Mensagem mensagem = (Mensagem) marshaller.unmarshal(reader);
            System.out.println("Validando: " + mensagem);

            Validator validator = schema.newValidator();
            JAXBSource source = new JAXBSource(jaxbContext, mensagem);
            validator.validate(source);
            return true;
        } catch (IOException | SAXException | JAXBException ex) {
            Logger.getLogger(MessageValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

}
