package reviewme;

import java.util.*;

public class ReviewMe {
	static Scanner scanner = new Scanner(System.in);
	static ArrayList <Gebruiker> gebruikers = new ArrayList<>(); 
	static ArrayList <Item> items = new ArrayList<>();
	static int gebruikerId = 2;
	static int uploadCount;
	private int keuze;
	private int itemId;

	//starten van de applicatie
	void start(){		
		laadGebruikers(); //inladen van gebruikers
		laadUploads(); //inladen van uploads
		laadDummies();//Inladen van gegeven cijfers (Dummies)
		gebruikersInfoMenu();

		//System.out.println("Welcome bij ReviewMe!");
		//System.out.println("=====================\n");
		//login();

	}
	void login(){
		boolean loginValidated = false;
		while(loginValidated == false){
			System.out.print("Gebruikersnaam: ");
			String gebruikersnaam = scanner.nextLine();
			System.out.print("Password: ");
			String wachtwoord = scanner.nextLine();
			boolean gelijkeGebruikersnaam = false;
			for(int i=0; i<gebruikers.size(); i++){
				if(gebruikersnaam.equals(gebruikers.get(i).getGebruikersNaam())){
					gelijkeGebruikersnaam = true;
					if(wachtwoord.equals(gebruikers.get(i).getWachtwoord())){
						loginValidated = true;
						gebruikerId = i;
					} else {
						System.out.println("Combinatie gebruikersnaam en/of wachtwoord onbekend\nProbeer het opnieuw...");
						scanner.nextLine();
					}
				} 
			}
			if(gelijkeGebruikersnaam == false){
				System.out.println("Combinatie gebruikersnaam en/of wachtwoord onbekend\nProbeer het opnieuw...");
				scanner.nextLine();
			}
		}
		System.out.println(gebruikers.get(gebruikerId).getGebruikersNaam()+" het inloggen is gelukt!");
		hoofdMenu();

	}

	//Menu om items te reviewen
	void hoofdMenu(){
		String categorie [] = {"Boeken","Muziek","Video's"};

		boolean keuzeGemaakt = false;
		while (keuzeGemaakt == false){
			System.out.println("Maak een keuze:");
			System.out.println("===============");
			for (int i = 0; i < categorie.length; i++) {
				System.out.println((i+1)+" : "+categorie[i]);
			}
			//optie voor afsluiten of in geval van uploader naar hoofdmenu gaan.
			if (gebruikers.get(gebruikerId) instanceof Uploader){
				System.out.println("U : Upload item");
			}
			System.out.println("I : Gebruikersinfo");
			System.out.println("L : Log uit");
			System.out.println("X : Sluit af");



			System.out.println();		
			String keuze = scanner.nextLine().toUpperCase();

			switch(keuze){
			case "X":
				System.exit(1);
				break;
			case "1":
				keuzeGemaakt = true;
				boekenLijst();
				break;
			case "2":
				keuzeGemaakt = true;
				songLijst();
				break;
			case "3":
				keuzeGemaakt = true;
				videoLijst();
				break;
			case "U":
				if(gebruikers.get(gebruikerId) instanceof Uploader){
					System.out.println("uploadMenu");
					keuzeGemaakt = true;
					break;
				} else {
					System.out.println("Deze keuze zit er niet tussen. Probeer opnieuw...");
					keuzeGemaakt = false;
					break;
				}

			case "I":
				keuzeGemaakt = true;
				gebruikersInfoMenu();
				break;
			case "L":
				keuzeGemaakt = true;
				System.out.println("U bent nu uitgelogd. \n====================\nDruk op een toets om door te gaan...");

				scanner.nextLine();
				login();				
				break;


			default:
				System.out.println("Deze keuze zit er niet tussen. Probeer opnieuw...");
				keuzeGemaakt = false;
				break;
			}
		} while (keuzeGemaakt == false);
	}

	//invoer moet een int zijn anders moet er opnieuw ingevoerd worden
	void valideerInvoer(){
		keuze = -1;
		try {
			keuze = scanner.nextInt();
		} catch (InputMismatchException ime){
			scanner.nextLine();
		}
	}

	//Menu per categorie
	void boekenLijst(){
		int aantalBoeken = 0;
		for(int i=0; i < items.size(); i++){
			if(items.get(i) instanceof Boek){
				aantalBoeken++;
			}
		}
		int boekKeuze = 0;
		int [] boekenlijst = new int[aantalBoeken];
		boolean keuzeGemaakt = false;
		while(keuzeGemaakt == false){
			System.out.println("Kies een boek:");
			System.out.println("============================");
			int index = 0;
			for (int i = 0; i < items.size(); i++) {
				if(items.get(i) instanceof Boek){
					System.out.println((index+1)+" : "+ items.get(i).getTitel());
					boekenlijst[index] = i;
					index++;
				}
			}
			System.out.println("0 : Terug naar vorig menu");
			valideerInvoer();
			boekKeuze = keuze;
			for (int i = 0; i < boekenlijst.length; i++) {
				if(boekKeuze == (i+1)){
					keuzeGemaakt = true;
					boekKeuze--;
				}
			}
			if(keuze == 0){
				hoofdMenu();
				keuzeGemaakt = true;
			}else if (!keuzeGemaakt){
				System.out.println("Verkeerde invoer. Probeer opnieuw...");
			}
		}
		itemId = boekenlijst[boekKeuze];
		itemMenu();
	}
	void videoLijst(){
		int aantalVideos = 0;
		for(int i=0; i < items.size(); i++){
			if(items.get(i) instanceof Video){
				aantalVideos++;
			}
		}
		int videoKeuze = 0;
		int [] videolijst = new int[aantalVideos];
		boolean keuzeGemaakt = false;
		while(keuzeGemaakt == false){
			System.out.println("Kies een video:");
			System.out.println("============================");
			int index = 0;
			for (int i = 0; i < items.size(); i++) {
				if(items.get(i) instanceof Video){
					System.out.println((index+1)+" : "+ items.get(i).getTitel());
					videolijst[index] = i;
					index++;
				}
			}
			System.out.println("0 : Terug naar vorig menu");
			valideerInvoer();
			videoKeuze = keuze;
			for (int i = 0; i < videolijst.length; i++) {
				if(videoKeuze == (i+1)){
					keuzeGemaakt = true;
					videoKeuze--;
				}
			}
			if(keuze == 0){
				hoofdMenu();
				keuzeGemaakt = true;
			}else if (!keuzeGemaakt){
				System.out.println("Verkeerde invoer. Probeer opnieuw...");
			}
		}
		itemId = videolijst[videoKeuze];
		itemMenu();
	}
	void songLijst(){
		int aantalSongs = 0;
		for(int i=0; i < items.size(); i++){
			if(items.get(i) instanceof Song){
				aantalSongs++;
			}
		}
		int songKeuze = 0;
		int [] songlijst = new int[aantalSongs];
		boolean keuzeGemaakt = false;
		while(keuzeGemaakt == false){
			System.out.println("Kies een lied:");
			System.out.println("============================");
			int index = 0;
			for (int i = 0; i < items.size(); i++) {
				if(items.get(i) instanceof Song){
					System.out.println((index+1)+" : "+ items.get(i).getTitel());
					songlijst[index] = i;
					index++;
				}
			}
			System.out.println("0 : Terug naar vorig menu");
			valideerInvoer();
			songKeuze = keuze;
			for (int i = 0; i < songlijst.length; i++) {
				if(songKeuze == (i+1)){
					keuzeGemaakt = true;
					songKeuze--;
				}
			}
			if(keuze == 0){
				hoofdMenu();
				keuzeGemaakt = true;
			}else if (!keuzeGemaakt){
				System.out.println("Verkeerde invoer. Probeer opnieuw...");
			}
		}
		itemId = songlijst[songKeuze];
		itemMenu();
	}


	//menu van een item zelf
	void itemMenu(){
		boolean keuzeGemaakt = false;
		while (keuzeGemaakt == false){
			System.out.println("U heeft gekozen voor:\n"+ items.get(itemId).getTitel());
			System.out.println("============================");
			System.out.println("1 : Info over item");
			System.out.println("2 : Geef een cijfer");
			System.out.println("0 : Ga terug naar vorig menu");
			valideerInvoer();
			switch(keuze){
			case 1:
				System.out.println("Infomenu");
				keuzeGemaakt = true;
				break;
			case 2:
				cijferGeven();
				keuzeGemaakt = true;
				break;
			case 0:
				if(items.get(itemId) instanceof Boek){
					boekenLijst();
				}else if(items.get(itemId) instanceof Song){
					songLijst();
				}else if(items.get(itemId) instanceof Video){
					videoLijst();
				}
				keuzeGemaakt = true;
				break;
			default:
				System.out.println("Verkeerde invoer. Probeer opnieuw...");
				keuzeGemaakt = false;
				break;
			}
		}

	}

	//methode voor het geven van een cijfer
	void cijferGeven(){
		int cijfer;
		//loop ter verificatie van een juiste invoer
		boolean keuzeGemaakt = false;
		while(keuzeGemaakt == false){
			boolean cijferAlGegeven = false;
			int cijferIndexGebruiker = 0;
			int cijferIndexItem = 0;
			System.out.println(items.get(itemId).getTitel());
			System.out.println("============================");
			//Kijken of item al beoordeeld is door Gebruiker

			for(int i = 0; i < gebruikers.get(gebruikerId).getItemId().size(); i++){	
				if(itemId == (int) gebruikers.get(gebruikerId).getItemId().get(i)){
					cijferIndexGebruiker = i;
					cijferIndexItem = items.get(itemId).getGebruikerId().indexOf(gebruikerId);
					cijferAlGegeven = true;
					System.out.println("U heeft eerder een "+ gebruikers.get(gebruikerId).getCijferlijst().get(i)+".0 gegeven.");	

				}
			}
			System.out.println(cijferIndexGebruiker);
			System.out.println(cijferIndexItem);
			//Geven van een cijfer
			System.out.println("Geef een cijfer van 0 t/m 10:");
			valideerInvoer();//controle of het een geldige invoer is
			if(keuze >= 0 && keuze <11){
				cijfer = keuze;
				System.out.println("U heeft een "+ cijfer + ".0 gegeven.");
				System.out.println("Druk op een toets om verder te gaan...");
				System.out.println();
				scanner.nextLine();
				//cijfer toevoegen indien gebruiker nog niet eerder cijfer heeft gegeven
				//cijfer wijzigen indien gebruiker item al eerder een cijfer heeft gegeven
				if(cijferAlGegeven){
					gebruikers.get(gebruikerId).changeCijfer(cijferIndexGebruiker, itemId, cijfer);
					items.get(itemId).changeCijfer(cijferIndexItem, gebruikerId, cijfer);
				}else{
					gebruikers.get(gebruikerId).addCijfer(itemId, cijfer);
					items.get(itemId).addCijfer(gebruikerId, cijfer);
				}				
				keuzeGemaakt = true;
			} else {
				System.out.println("Verkeerde invoer. Probeer opnieuw...");
				keuzeGemaakt = false;
			}
		}
		itemMenu();
	}

	//laden van vooraf ingevoerde gegevens (gebruikers en uploads)
	void laadGebruikers(){
		gebruikers.add((Reviewer)new Reviewer("Manuel","123"));//0
		gebruikers.add((Reviewer)new Reviewer("Bertho","123"));//1
		gebruikers.add((Uploader)new Uploader("Jase","345"));//2
		gebruikers.add((Uploader)new Uploader("Iwan","345"));//3
	}
	void laadUploads(){
		items.add((Boek)new Boek(0,"Wetten der Magie"));
		items.add((Boek)new Boek(1,"Yab Yum"));
		items.add((Boek)new Boek(2,"Het testament"));
		items.add((Song)new Song(3,"Brindis"));
		items.add((Song)new Song(4,"Hold up"));
		items.add((Song)new Song(5,"Bohemian Rhapsody"));
		items.add((Video)new Video(6,"Card flourishes"));
		items.add((Video)new Video(7,"Shawshank Redemption"));
		items.add((Video)new Video(8,"How to make cocktails"));
		items.add((Boek)new Boek(9,"Max Havelaar"));

		uploadCount = items.size();

	}

	//menu voor het krijgen van gebruikersinfo
	void gebruikersInfoMenu(){
		boolean keuzeGemaakt = false;
		while (keuzeGemaakt == false){
			System.out.print("Ingelogde gebruiker: ");

			System.out.println(gebruikers.get(gebruikerId).getGebruikersNaam());
			System.out.println("=====================");
			System.out.println("1 : Wijzig gegevens");
			System.out.println("2 : Gegeven cijfers");
			if(gebruikers.get(gebruikerId) instanceof Uploader){
				System.out.println("3 : Bekijk je uploads");
			}
			System.out.println("0 : Hoofdmenu");
			valideerInvoer();
			switch(keuze){
			case 0:
				hoofdMenu();
				keuzeGemaakt = true;
				break;
			case 1:
				System.out.println("Wijzig gegevens");
				keuzeGemaakt = true;
				break;
			case 2:
				gegevenCijfers();
				keuzeGemaakt = true;
				break;
			case 3:
				if(gebruikers.get(gebruikerId) instanceof Uploader){
					System.out.println("Hier komt uploadMenu");
					keuzeGemaakt = true;
					break;
				} else {
					System.out.println("Verkeerde invoer. Probeer opnieuw...");
					keuzeGemaakt = false;
					break;
				}
			default:
				System.out.println("Verkeerde invoer. Probeer opnieuw...");
				keuzeGemaakt = false;
				break;
			}
		}
	}
	void gegevenCijfers(){
		System.out.println("U heeft de volgende cijfers gegeven:");
		System.out.println("====================================");
		
		for (int i = 0; i < gebruikers.get(gebruikerId).getCijferlijst().size(); i++) {
			int idOfItem = (int) gebruikers.get(gebruikerId).getItemId().get(i);
			System.out.println(items.get(idOfItem).getClass().getSimpleName());
			System.out.println(items.get(idOfItem).getTitel());
			System.out.println("cijfer: " + gebruikers.get(gebruikerId).getCijferlijst().get(i)+".0");
			System.out.println("------------------------------------");
		}
		System.out.println("Druk op een toets om verder te gaan...");
		scanner.nextLine();
		scanner.nextLine();
		gebruikersInfoMenu();
	}
	////////////////////
	////////////////////
	////////////////////
	////////////////////
	void laadDummies(){
		
		//gebruiker 2
		gebruikers.get(2).addCijfer(0, 8);
		items.get(0).addCijfer(2, 8);		
		gebruikers.get(2).addCijfer(1, 7);
		items.get(1).addCijfer(2, 7);
		gebruikers.get(2).addCijfer(2, 6);
		items.get(2).addCijfer(2, 6);
		gebruikers.get(2).addCijfer(3, 5);
		items.get(3).addCijfer(2, 5);
		gebruikers.get(2).addCijfer(4, 6);
		items.get(4).addCijfer(2, 6);
		gebruikers.get(2).addCijfer(5, 8);
		items.get(5).addCijfer(2, 8);
		gebruikers.get(2).addCijfer(6, 9);
		items.get(6).addCijfer(2, 9);
		gebruikers.get(2).addCijfer(7, 4);
		items.get(7).addCijfer(2, 4);
		gebruikers.get(2).addCijfer(8, 3);
		items.get(8).addCijfer(2, 3);
		gebruikers.get(2).addCijfer(9, 3);
		items.get(9).addCijfer(2, 6);
		
		//gebruiker 0
		gebruikers.get(0).addCijfer(0, 10);
		items.get(0).addCijfer(0, 10);		
		gebruikers.get(0).addCijfer(1, 6);
		items.get(1).addCijfer(0, 6);
		gebruikers.get(0).addCijfer(2, 6);
		items.get(2).addCijfer(0, 6);
		gebruikers.get(0).addCijfer(3, 7);
		items.get(3).addCijfer(0, 7);
		gebruikers.get(0).addCijfer(4, 5);
		items.get(4).addCijfer(0, 5);
		gebruikers.get(0).addCijfer(5, 3);
		items.get(5).addCijfer(0, 3);
		gebruikers.get(0).addCijfer(6, 5);
		items.get(6).addCijfer(0, 5);
		gebruikers.get(0).addCijfer(7, 5);
		items.get(7).addCijfer(0, 5);
		gebruikers.get(0).addCijfer(8, 6);
		items.get(8).addCijfer(0, 6);
		gebruikers.get(0).addCijfer(9, 2);
		items.get(9).addCijfer(0, 2);
		
		//gebruiker 1
		gebruikers.get(1).addCijfer(0, 7);
		items.get(0).addCijfer(1, 7);		
		gebruikers.get(1).addCijfer(1, 7);
		items.get(1).addCijfer(1, 7);
		gebruikers.get(1).addCijfer(2, 6);
		items.get(2).addCijfer(1, 6);
		gebruikers.get(1).addCijfer(3, 7);
		items.get(3).addCijfer(1, 7);
		gebruikers.get(1).addCijfer(4, 5);
		items.get(4).addCijfer(1, 5);
		gebruikers.get(1).addCijfer(5, 6);
		items.get(5).addCijfer(1, 6);
		gebruikers.get(1).addCijfer(6, 7);
		items.get(6).addCijfer(1, 7);
		gebruikers.get(1).addCijfer(7, 5);
		items.get(7).addCijfer(1, 5);
		gebruikers.get(1).addCijfer(8, 8);
		items.get(8).addCijfer(1, 8);
		gebruikers.get(1).addCijfer(9, 5);
		items.get(9).addCijfer(1, 5);
		
		//gebruiker 3
		gebruikers.get(3).addCijfer(0, 8);
		items.get(0).addCijfer(3, 8);		
		gebruikers.get(3).addCijfer(1, 8);
		items.get(1).addCijfer(3, 8);
		gebruikers.get(3).addCijfer(2, 4);
		items.get(2).addCijfer(3, 4);
		gebruikers.get(3).addCijfer(3, 7);
		items.get(3).addCijfer(3, 7);
		gebruikers.get(3).addCijfer(4, 9);
		items.get(4).addCijfer(3, 9);
		gebruikers.get(3).addCijfer(5, 9);
		items.get(5).addCijfer(3, 9);
		gebruikers.get(3).addCijfer(6, 5);
		items.get(6).addCijfer(3, 5);
		gebruikers.get(3).addCijfer(7, 7);
		items.get(7).addCijfer(3, 7);
		gebruikers.get(3).addCijfer(8, 7);
		items.get(8).addCijfer(3, 7);
		gebruikers.get(3).addCijfer(9, 6);
		items.get(9).addCijfer(3, 6);
	}
}
