package eu.telecomnancy;

import java.rmi.registry.*;
import java.rmi.*;
import java.rmi.server.*;

public class Server {

	// lancement du serveur
	// qui s'enregistre sur le rmiregistry en local
	public static void main(String args[]) {
		// Cr�er et installer un gestionnaire de s�curit�
		// System.setSecurityManager(new RMISecurityManager());
		try {
			// creation d'un objet serveur
			RandomListenSensor randomSensor = new RandomListenSensor();

			// génération du proxy
			ISensorObservable stubRandomSensor = (ISensorObservable) UnicastRemoteObject.exportObject(randomSensor, 0);

			// récupération du registre
			Registry reg = LocateRegistry.getRegistry();

			// enregistrement du stub sur rmi
			reg.rebind("randomSensor1", stubRandomSensor);
			System.out.println("le RandomSensor est introduit dans le RMI");
			
		} catch (Exception e) {
			System.out.println("Erreur !  RandomSensor : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
