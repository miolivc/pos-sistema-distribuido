package br.edu.ifpb.pos.entidade;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceProvider extends Remote {
    
    void register(Service service) throws RemoteException;
    <T> T lookup(String service, Class clazz) throws RemoteException;
//    boolean validSchema(String message) throws RemoteException;
    
}
