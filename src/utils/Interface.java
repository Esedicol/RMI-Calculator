package utils;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;


public interface Interface extends Remote {
    // Calculator Operations
    int add(int x, int y) throws RemoteException, ServerNotActiveException;
    int subtract(int x, int y) throws RemoteException, ServerNotActiveException;
    int multiply(int x, int y) throws RemoteException, ServerNotActiveException;
    int divide(int x, int y) throws RemoteException, ServerNotActiveException;
}
