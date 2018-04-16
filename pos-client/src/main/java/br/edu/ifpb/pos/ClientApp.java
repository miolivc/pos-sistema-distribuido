package br.edu.ifpb.pos;

import br.edu.ifpb.pos.entidade.Mensagem;
import br.edu.ifpb.pos.entidade.MessageValidator;
import br.edu.ifpb.pos.entidade.MessengerService;
import br.edu.ifpb.pos.entidade.ServiceProvider;
import java.io.StringWriter;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.xml.sax.SAXException;

public class ClientApp {

    static Mensagem message = new 
        Mensagem("michelle", "wellington", LocalDateTime.now(), "Opa lele");

    public static void main(String[] args) 
            throws RemoteException, NotBoundException, SAXException, JAXBException {

        ServiceProvider provider = (ServiceProvider) 
                LocateRegistry.getRegistry("localhost", 10999)
                              .lookup("provider");

//        File xmlValidator = new File("src/main/resources/message.xsd");
//        String xmlMessage = buildMessage(message, xmlValidator);
        String xmlMessage = buildMessage(message);
        sendMessage(xmlMessage, provider);
    }

    public static String buildMessage(Mensagem message) 
            throws SAXException, JAXBException {
        
        JAXBContext jaxbContext = JAXBContext.newInstance(Mensagem.class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

//        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        Schema schema = sf.newSchema(xmlValidator);

        StringWriter writer = new StringWriter();
        
//        marshaller.setSchema(schema);
        marshaller.marshal(message, writer);

        return writer.toString();
    }

    public static void sendMessage(String xmlMessage, ServiceProvider provider) throws RemoteException {
        MessengerService messenger = provider.lookup("messenger", MessengerService.class);
        if (MessageValidator.validate(xmlMessage)) {
            messenger.sendMessage(xmlMessage);
        } else {
            System.err.println("Xml errado irmao");
        }
    }
}
