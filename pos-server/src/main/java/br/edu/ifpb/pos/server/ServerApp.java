/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.server;

import br.edu.ifpb.pos.entidade.Service;
import br.edu.ifpb.pos.entidade.ServiceProvider;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author miolivc
 */
public class ServerApp {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {

        Service service = new Service("messenger", "127.0.0.1", 10998);
        ServiceProvider provider = (ServiceProvider) 
                LocateRegistry.getRegistry("127.0.0.1", 10999).lookup("provider");
        provider.register(service);

        Messenger messenger = new Messenger(provider);

        Registry registry = LocateRegistry.createRegistry(10998);
        registry.bind("messenger", messenger);
        System.out.println("Servidor Ativo!");
    }

}
