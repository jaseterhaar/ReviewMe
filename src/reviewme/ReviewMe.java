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


	void start(){		
		laadGebruikers(); //inladen van gebruikers
		laadUploads(); //inladen van uploads


		System.out.println("Welcome bij ReviewMe!");
		System.out.println("=====================\n");
		login();
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
				System.out.println((i+1)+": "+categorie[i]);
			}
			//optie voor afsluiten of in geval van uploader naar hoofdmenu gaan.
			if (gebruikers.get(gebruikerId) instanceof Uploader){
				System.out.println("7: Upload item");
			}
			System.out.println("8: Gebruikersinfo");
			System.out.println("9: Log uit");
			System.out.println("0: Sluit af");



			System.out.println();		
			int keuze = 0;
			try {
				keuze = scanner.nextInt();
			} catch (InputMismatchException ime){
				scanner.next();
			}

			switch(keuze){
			case 0:
				System.exit(1);
				break;
			case 1:
				keuzeGemaakt = true;
				boekenLijst();
				break;
			case 2:
				keuzeGemaakt = true;
				songLijst();
				break;
			case 3:
				keuzeGemaakt = true;
				videoLijst();
				break;
			case 7:
				if(gebruikers.get(gebruikerId) instanceof Uploader){
					System.out.println("uploadMenu");
					keuzeGemaakt = true;
					break;
				} else {
					System.out.println("Deze keuze zit er niet tussen. Probeer opnieuw...");
					keuzeGemaakt = false;
					break;
				}

			case 8:
				keuzeGemaakt = true;
				System.out.println("GebruikersInfo");
				break;
			case 9:
				keuzeGemaakt = true;
				System.out.println("U bent nu uitgelogd. \n====================\nVul onderstaande gegevens in om in te loggen:");

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


}
