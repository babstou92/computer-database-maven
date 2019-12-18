package com.excilys.ui;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.excilys.models.Company;
import com.excilys.models.Computer;
import com.excilys.service.ServiceCompany;
import com.excilys.service.ServiceComputer;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	private static ServiceCompany serviceCompany = ServiceCompany.getServiceCompany();
	private static ServiceComputer serviceComputer = ServiceComputer.getServiceCOmputer();
	private static String choixQuitter;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	private static int choixCommande;
	private static int choixId;
	private static int [] tableauId = new int[1];
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class); 

	
	public static void main(String[] args) throws ClassNotFoundException {
		
		BasicConfigurator.configure();
		LOGGER.info("Start ...");

		boucleExt : do {
			
			firstChoice();
			choixCommande = scanner.nextInt();
		
			if(1 <= choixCommande && choixCommande <= 4 ) {		
				switch (choixCommande) {
				case 1:
					List<Company> listCompany = serviceCompany.findAllCompany();
					for(Company company : listCompany) {
						System.out.println(company.toString());
					}
					break;
				case 2:
					List<Computer> listComputer = serviceComputer.findAllComputer();
					for(Computer computer : listComputer) {
						System.out.println(computer.toString());
					}
					break;
				case 3:
					System.out.println("Veuillez choisir un Id");
					choixId = scanner.nextInt();
					tableauId[0] = choixId;
					LOGGER.debug("L'id choisit est "+ choixId);
					System.out.println(serviceComputer.findOneComputer(choixId).toString());
					break;
				case 4:
				    newEntry();
					break;
				}
			
				if(choixCommande == 3) {
					do { 
						
						secondChoice();
						choixCommande = scanner.nextInt();
						
						if(choixCommande == 1 || choixCommande == 2) {		
							switch (choixCommande) {
								case 1: 
								    updateComputer();
									break;
								case 2:
									serviceComputer.deleteOneComputer(tableauId[0]);
									break;
							}
					
						} else { 
							System.out.println("Commande incorrecte");
						}
						
						System.out.println("Continuez ? (Y/N)");
						choixQuitter = scanner.next();
						
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
	
	private static void newEntry() {
		System.out.println("Veuillez saisir les caracteristiques du nouveau computer dans l'ordre: "  );
	    System.out.println( "nom du computer" );
	    String computerName = scanner.next();
        System.out.println("la date de fabrication au format d/MM/YYYY");
        String dateString= scanner.next();                                    
        LocalDate introduced = LocalDate.parse(dateString, formatter);
        System.out.println("la date de mise hors-service au format d/MM/YYYY");
        dateString = scanner.next();
        LocalDate discontinued = LocalDate.parse(dateString, formatter);
        System.out.println("l'id de la company");
        int company_id = scanner.nextInt();
		serviceComputer.createOneComputer(  new Computer.ComputerBuilder().name(computerName).introducedDate(introduced)
														.discontinuedDate(discontinued).company(new Company.CompanyBuilder()
														.idCompany(company_id).build()).build());
	}
	

	
	private static void updateComputer() {
		System.out.println( "valeur de choixId: "+ tableauId[0] );
	    System.out.println("Veuillez saisir dans l'ordre, le nom du computer");
	    String computerName = scanner.next();
	    System.out.println("la date de fabrication au format d/MM/YYYY");
        String dateString= scanner.next();                                    
        LocalDate introduced = LocalDate.parse(dateString, formatter);
        System.out.println("la date de mise hors-service au format d/MM/YYYY");
        dateString = scanner.next();
        LocalDate discontinued = LocalDate.parse(dateString, formatter);
        System.out.println("l'id de la company");
        int company_id = scanner.nextInt();
        serviceComputer.updateOneComputer(  new Computer.ComputerBuilder().idComputer(tableauId[0]).name(computerName).introducedDate(introduced)
														.discontinuedDate(discontinued).company(new Company.CompanyBuilder()
														.idCompany(company_id).build()).build()); 
	}
	
	private static void firstChoice() {
		System.out.println("Voici la liste de commandes que vous pouvez taper et leurs actions dans la base de donnée :");
		System.out.println(" 1 -> Afficher toutes les company "
						+ " \n 2 -> Afficher tous les computers "
						+ " \n 3 -> Afficher les infomations d'un computer "
						+ " \n 4 -> Créer une nouvelle entrée computer dans la base de donnée");
	
		System.out.println("Veuillez saisir une commande");		
	}
	
	private static void secondChoice() {
		System.out.println("\t Voici la liste de commandes que vous pouvez taper et leurs actions sur le computer récupéré :");
		System.out.println("\t 1 -> Mettre à jour une ou plusieurs données du computer séléctionné"
							+ "\n\t 2 -> Supprimer le computer séléctionné ");
		
		System.out.println("Veuillez saisir une commande");
	}

}

