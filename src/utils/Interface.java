package utils;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;


public interface Interface extends Remote {
    // Calculator Operations
    double add(double x, double y) throws RemoteException, ServerNotActiveException;
    double subtract(double x, double y) throws RemoteException, ServerNotActiveException;
    double multiply(double x, double y) throws RemoteException, ServerNotActiveException;
    double divide(double x, double y) throws RemoteException, ServerNotActiveException;
}
