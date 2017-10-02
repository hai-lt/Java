//package views.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImplement implements AtClient {
    public AtServer atServer;
    public String hostname;

    public ClientImplement(String hostname) {
        this.hostname = hostname;
    }
    @Override
    public void connect() throws RemoteException {
        UnicastRemoteObject.exportObject(this);
        try {
            atServer = (AtServer) Naming.lookup("rmi://" + hostname+ "/serverobject");
            atServer.registerClient(this);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() throws RemoteException {
        try {
            atServer.injectClient(this);
        } catch (Exception e) {}
    }



    @Override
    public int clientAdd(ArraySerializable arraySerializable) throws RemoteException {
        System.out.println("In clientimplemnt");
        arraySerializable.show();
        int sum = arraySerializable.sum();
        System.out.println("Sum of " + arraySerializable.values.toString() + "My result is: " + sum);
        System.out.println("end client implement");
        return sum;
    }

    public int requestSolving(ArraySerializable arraySerializable) throws RemoteException {
        try {

            atServer = (AtServer) Naming.lookup("rmi://" + hostname+ "/serverobject");
        } catch (Exception e) {}
        return atServer.sendArray(arraySerializable);
    }
}
