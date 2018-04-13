/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.server;

import br.edu.ifpb.pos.entidade.MessengerService;
import br.edu.ifpb.pos.entidade.ServiceProvider;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author miolivc
 */
public class Messenger extends UnicastRemoteObject implements MessengerService {

    private ServiceProvider provider;

    public Messenger(ServiceProvider provider) throws RemoteException {
        this.provider = provider;
    }

    @Override
    public void sendMessage(String message) throws RemoteException {

        if (provider.validSchema(message)) {
            System.out.println("Processando..." + message);
        } else {
            System.out.println("Xml errado parsa!");
        }

//        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = sf.newSchema(new File("src/main/java/ads/pos/xml/funcionario/source/funcionario.xsd"));
//
//            File file = new File("src/main/java/ads/pos/xml/funcionario/source/funcionario.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(Funcionario.class);
//            Unmarshaller marshaller = jaxbContext.createUnmarshaller();
//            marshaller.setSchema(schema);
//
//            Funcionario funcionario = (Funcionario) marshaller.unmarshal(file);
//            System.out.println(funcionario);
    }

}
