package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import frames.ServerFrame;
import utils.Interface;

/**
 * @author Emmanuel Sedicol
 */
public class Server extends UnicastRemoteObject implements Interface {
    static ServerFrame sf = new ServerFrame();
    public static boolean connected = Client.connect();

    public Server() throws RemoteException {
        super();
    }

    // Calculator operations implemented from our interface
    @Override
    public int add(int num1, int num2) throws RemoteException, ServerNotActiveException {
    	// ouput operations called
        sf.displayOperations("\n" + new Date() + " [" + getClientHost() + "] Add Method Called: (" + num1 + " + " + num2 + ")");
        int ans = num1 + num2;
        // output our response to client
        sf.displayOperations(new Date() + "Response to [" + getClientHost() + "]: " + ans);
        return ans;
    }

    @Override
    public int subtract(int num1, int num2) throws RemoteException, ServerNotActiveException {
    	// ouput operations called
        sf.displayOperations("\n" + new Date() + " [" + getClientHost() + "] Subtraction Method Called: (" + num1 + " - " + num2 + ")");
        int ans = num1 - num2;
     // output our response to client
        sf.displayOperations(new Date() + "Response to [" + getClientHost() + "]: " + ans);
        return ans;
    }

    @Override
    public int multiply(int num1, int num2) throws RemoteException, ServerNotActiveException {
    	// ouput operations called
        sf.displayOperations("\n" + new Date() + " [" + getClientHost() + "] Multiply Method Called: (" + num1 + " x " + num2 + ")");
        int ans = num1 * num2;
     // output our response to client
        sf.displayOperations(new Date() + "Response to [" + getClientHost() + "]: " + ans);
        return ans;
    }

    @Override
    public int divide(int num1, int num2) throws RemoteException, ServerNotActiveException {
    	// ouput operations called
        sf.displayOperations("\n" + new Date() + " [" + getClientHost() + "] Division Method Called: (" + num1 + " / " + num2 + ")");
        int ans = num1 / num2;
     // output our response to client
        sf.displayOperations(new Date() + "Response to [" + getClientHost() + "]: " + ans);
        return ans;
    }


    public static void main(String[] args) throws RemoteException  {
        boolean serverRunning = false;

        try{
            // running application on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Calculator", new Server());

            // display status
            sf.displayOperations(" Starting Calculator Server ......");
            for(int i = 0; i < 2; i++) {
            	sf.displayOperations(" Starting ......");
            	TimeUnit.SECONDS.sleep(1);
            }
            serverRunning = true;
            
            TimeUnit.SECONDS.sleep(2);
            sf.message.setText("");
        } catch (Exception e) {
            System.out.print(e);
        }

        // checking if server is running or not. Displays outcome
        if(serverRunning == true) {
            sf.displayOperations("YAY Calculator Server is now running :)");
        } else {
            sf.displayOperations("Oh nooo server failed to run :(");
        }
    }
}

