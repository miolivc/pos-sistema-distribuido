package br.edu.ifpb.pos;

import br.edu.ifpb.pos.entidade.Service;
import br.edu.ifpb.pos.entidade.ServiceProvider;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
    public boolean validate(String message) throws RemoteException {
        return true;
    }

    @Override
    public void register(Service service) throws RemoteException {
        services.add(service);
    }

}
