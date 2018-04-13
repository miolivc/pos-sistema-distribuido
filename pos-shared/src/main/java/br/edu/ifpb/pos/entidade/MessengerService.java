
package br.edu.ifpb.pos.entidade;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessengerService extends Remote {
    
    void sendMessage(String message) throws RemoteException;
    
}
