package br.edu.ifpb.pos;

import br.edu.ifpb.pos.entidade.MessengerService;
import br.edu.ifpb.pos.entidade.ServiceProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ClientApp {
    
//    <mensagem>
//        <para>michelle</para>
//        <de>wellington</de>
//        <criadoEm> 21/11/2017 13:00:00</criadoEm>
//        <corpo> Oi, sou um Home</corpo>
//    </mensagem>
    static Map<String, Object> message = new HashMap<>();

    public static void main(String[] args) throws RemoteException, NotBoundException, JsonProcessingException {

        message.put("para", "wellington");
        message.put("de", "michelle");
        message.put("corpo", "oi");
        message.put("criadoEm", LocalDateTime.now());
        
        ServiceProvider provider = (ServiceProvider) LocateRegistry.getRegistry("localhost", 10999)
                .lookup("provider");

        String xmlMessage = buildMessage(message);
        sendMessage(xmlMessage, provider);
    }

    public static String buildMessage(Map<String, Object> message) throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();
        String mesageXml = mapper.writeValueAsString(message);
        System.out.println("XML: \n" + mesageXml);
        return mesageXml;
    }

    public static void sendMessage(String xmlMessage, ServiceProvider provider) throws RemoteException {
        MessengerService messenger = provider.lookup("messenger", MessengerService.class);

        if (provider.validate(xmlMessage)) {
            messenger.sendMessage(xmlMessage);
            System.out.println("Mensagem Enviada: " + xmlMessage);
        } else {
            System.out.println("XML invalido!");
        }
    }
}
