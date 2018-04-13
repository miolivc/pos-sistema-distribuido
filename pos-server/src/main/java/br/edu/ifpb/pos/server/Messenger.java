/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.server;

import br.edu.ifpb.pos.entidade.MessengerService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author miolivc
 */
public class Messenger extends UnicastRemoteObject implements MessengerService {

    public Messenger() throws RemoteException {
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.print(message);
    }
    
}
