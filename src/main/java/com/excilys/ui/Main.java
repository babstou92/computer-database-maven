package com.excilys.ui;

import java.sql.Date;
import java.util.Scanner;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;

public class Main {

	public static void main(String[] args) {
		
		//test	tjr test
		Scanner scanner = new Scanner(System.in);
		 String choixQuitter;
		int choixCommande;
		int choixId;
		int [] tableauId = new int[1];
		
		
		boucleExt : do {
			System.out.println("Voici la liste de commandes que vous pouvez taper et leurs actions dans la base de donnée :");
			System.out.println(" 1 -> Afficher toutes les company "
							+ " \n 2 -> Afficher tous les computers "
							+ " \n 3 -> Afficher les infomations d'un computer "
							+ " \n 4 -> Créer une nouvelle entrée computer dans la base de donnée");
		
			System.out.println("Veuillez saisir une commande");		
			choixCommande = scanner.nextInt();
		
			if(1 <= choixCommande && choixCommande <= 4 ) {		
				switch (choixCommande) {
				case 1: 
					ServiceCompany.findAllCompany(); 
					break;
				case 2:
					ServiceComputer.findAllComputer();
					break;
				case 3:
					System.out.println("Veuillez choisir un Id");
					choixId = scanner.nextInt();
					tableauId[0] = choixId;
					ServiceComputer.findOneComputer(choixId);
					break;
				case 4:
				    System.out.println("Veuillez saisir les caracteristiques du nouveau computer dans l'ordre: "  );
				    System.out.println( "nom du computer" );
				    String computerName = scanner.next();
                    System.out.println("la date de fabrication au format yyyy-MM-dd");
                    String dateString= scanner.next();                                    
                    Date introduced = Date.valueOf(dateString);
                    System.out.println("la date de mise hors-service au format yyyy-MM-dd");
                    dateString = scanner.next();
                    Date discontinued = Date.valueOf( dateString );
                    System.out.println("l'id de la company");
                    int company_id = scanner.nextInt();
					ServiceComputer.createOneComputer(computerName, introduced, discontinued, company_id);
					break;
				}
			
				if(choixCommande == 3) {
					do { 
						System.out.println("\t Voici la liste de commandes que vous pouvez taper et leurs actions sur le computer récupéré :");
						System.out.println("\t 1 -> Mettre à jour une ou plusieurs données du computer séléctionné"
											+ "\n\t 2 -> Supprimer le computer séléctionné ");
						
						System.out.println("Veuillez saisir une commande");
						choixCommande = scanner.nextInt();
						
						if(choixCommande == 1 || choixCommande == 2) {		
							switch (choixCommande) {
								case 1: 
								    System.out.println( "valeur de choixId: "+ tableauId[0] );
								    System.out.println("Veuillez saisir dans l'ordre, le nom du computer");
								    String computerName = scanner.next();
								    System.out.println("la date de fabrication au format yyyy-MM-dd");
                                    String dateString= scanner.next();                                    
                                    Date introduced = Date.valueOf(dateString);
                                    System.out.println("la date de mise hors-service au format yyyy-MM-dd");
                                    dateString = scanner.next();
                                    Date discontinued = Date.valueOf( dateString );
                                    System.out.println("l'id de la company");
                                    int company_id = scanner.nextInt();
                                    ServiceComputer.updateOneComputer(computerName, introduced, discontinued, company_id, tableauId[0]); 
									break;
								case 2:
									ServiceComputer.deleteOneComputer(tableauId[0]);
									break;
							}
					
						} else { 
							System.out.println("Commande incorrecte");
						}
						
						System.out.println("Continuez ? (Y/N)");
						choixQuitter = scanner.next();
						
						//permet de sortir de la boucle ext
						if(choixQuitter.toUpperCase().equals("N")) break boucleExt;
						
					} while (choixQuitter.toUpperCase().equals("Y"));
				}
			
			} else { 
				System.out.println("Commande incorrecte");					
			}
		
			System.out.println("Continuez ? (Y/N)");
			choixQuitter = scanner.next();
			
		} while (choixQuitter.toUpperCase().equals("Y"));
		
			System.out.println("Vous avez quittez le menu. Adios");

		
		}

}

