import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import frames.ServerFrame;
import utils.Interface;

public class Server extends UnicastRemoteObject implements Interface {
	static ServerFrame sf = new ServerFrame();

	private Server() throws RemoteException {
		super();
	}

	
	// calculator operations implemented from our interface
	@Override
	public double add(double num1, double num2) throws RemoteException, ServerNotActiveException {
		sf.displayOperations("[" + getClientHost() + "] Add Method Called: (" + num1 + " + " + num2 + ")");
		return num1 + num2;
	}

	@Override
	public double subtract(double num1, double num2) throws RemoteException, ServerNotActiveException {
		sf.displayOperations("[" + getClientHost() + "] Subtraction Method Called: (" + num1 + " + " + num2 + ")");
		return num1 - num2;
	}

	@Override
	public double multiply(double num1, double num2) throws RemoteException, ServerNotActiveException {
		sf.displayOperations("[" + getClientHost() + "] Multiplication Method Called: (" + num1 + " + " + num2 + ")");
		return num1 * num2;
	}

	@Override
	public double divide(double num1, double num2) throws RemoteException, ServerNotActiveException {
		sf.displayOperations("[" + getClientHost() + "] Division Method Called: (" + num1 + " + " + num2 + ")");
		return num1 / num2;
	}


	public static void main(String[] args) throws RemoteException  {
		boolean serverRunning = false;

		try{
			// running application on port 1099
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("Calculator", new Server());

			// display status
			sf.displayOperations("Starting Server ......");
			serverRunning = true;
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

