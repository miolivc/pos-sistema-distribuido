package br.edu.ifpb.pos.entidade;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceProvider extends Remote {
    
    void register(Service service) throws RemoteException;
    <T> T lookup(String service, Class clazz) throws RemoteException;
    boolean validate(String message) throws RemoteException;
    
}
