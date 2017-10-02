//package views.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AtServer extends Remote {
    public void registerClient(AtClient client) throws RemoteException;
    public void injectClient(AtClient client) throws RemoteException;
    public int sendArray(ArraySerializable arraySerializable) throws RemoteException;
}
