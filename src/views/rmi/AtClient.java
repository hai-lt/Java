//package views.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AtClient extends Remote {
    public void connect() throws RemoteException;
    public void disconnect() throws RemoteException;
    public int clientAdd(ArraySerializable arraySerializable) throws RemoteException;
}
