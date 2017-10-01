//package views.rmi;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class ServerSetup {
    public static void main(String[] args) throws Exception {

        ServerImplement server = new ServerImplement();

        UnicastRemoteObject.exportObject(server);
        Naming.bind("rmi://localhost/serverobject",server);
        System.out.println("Waiting for client request ...");
    }

}
