package br.edu.ifpb.pos;

import br.edu.ifpb.pos.entidade.Mensagem;
import br.edu.ifpb.pos.entidade.Service;
import br.edu.ifpb.pos.entidade.ServiceProvider;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
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

public class Provider extends UnicastRemoteObject implements ServiceProvider {

    private final List<Service> services = new ArrayList<>();

    public Provider() throws RemoteException {
    }

    @Override
    public <T> T lookup(String service, Class clazz) throws RemoteException, AccessException {
        Service serviceInfo = null;

        for (Service serv : services) {
            if (serv.getName().equalsIgnoreCase(service)) {
                serviceInfo = serv;
                break;
            }
        }

        Object remoteObj = null;
        try {
            remoteObj = clazz.cast(
                    LocateRegistry.getRegistry(serviceInfo.getPort())
                            .lookup(serviceInfo.getName())
            );
        } catch (NotBoundException ex) {
            System.out.println("Erro no lookup:" + ex.getCause());
        }

        return (T) remoteObj;
    }

    @Override
    public boolean validSchema(String message) throws RemoteException {
//        return new File("src/main/resources/message.xsd");

        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("src/main/resources/message.xsd"));

            StringReader reader = new StringReader(message);

            JAXBContext jaxbContext = JAXBContext.newInstance(Mensagem.class);
            Unmarshaller marshaller = jaxbContext.createUnmarshaller();
            marshaller.setSchema(schema);

            Mensagem mensagem = (Mensagem) marshaller.unmarshal(reader);
            System.out.println(mensagem);
            
            Validator validator = schema.newValidator();
            JAXBSource source = new JAXBSource(jaxbContext, mensagem);
            validator.validate(source);
            return true;
        } catch (IOException | SAXException | JAXBException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void register(Service service) throws RemoteException {
        services.add(service);
    }

}
