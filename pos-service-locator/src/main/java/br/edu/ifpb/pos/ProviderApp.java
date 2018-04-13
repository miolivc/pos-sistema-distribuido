/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author miolivc
 */
public class ProviderApp {
    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        
        Provider provider = new Provider();
        Registry registry = LocateRegistry.createRegistry(10999);
        
        registry.bind("provider", provider);
        
        System.out.println("Provider está ativo!");
        
    }
    
}
