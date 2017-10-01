//package views.rmi;

import java.rmi.RemoteException;
import java.util.Vector;

public class ServerImplement implements AtServer {
    Vector<AtClient> clients;
    @Override
    public void registerClient(AtClient client) throws RemoteException {
        if (clients == null) {
            clients = new Vector<>();
        }
        clients.add(client);
        System.out.println("Client was connected: " + clients.size());
    }

    @Override
    public int sendArray(ArraySerializable arraySerializable) throws RemoteException {
        int sum = 0;
        System.out.println("Connected " + clients.size() + "servers");
        int medium = arraySerializable.size() / clients.size() + 1;
        for (int i = 0; i < clients.size(); i++) {
            int start = i * medium;
            int tail = (i + 1) * medium -  1;
            int sumClient = clients.get(i).clientAdd(arraySerializable.slice(start, tail));
            arraySerializable.slice(start, tail).show();
            System.out.println("Sum from " + start + " to " + tail + " is " + sumClient + " == real == " + arraySerializable.slice(start, tail).sum());
            sum += sumClient;
        }
        return sum;
    }
}
