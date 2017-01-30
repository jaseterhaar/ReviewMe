package reviewme;

import java.util.*;

public class ReviewMe {
	static Scanner scanner = new Scanner(System.in);
	static ArrayList <Gebruiker> gebruikers = new ArrayList<>(); 
	static ArrayList <Item> items = new ArrayList<>();
	static int gebruikersId = 2;
	static int uploadCount;
	private int keuze;
	private int itemId;


	void start(){		
		laadGebruikers(); //inladen van gebruikers
		laadUploads(); //inladen van uploads
		boekenLijst();

		/*
		System.out.println("Welcome bij ReviewMe!");
		System.out.println("=====================\n");
		login();
		if(gebruikers.get(gebruikersIdSession) instanceof Reviewer){
			reviewMenu();
		} else if (gebruikers.get(gebruikersIdSession) instanceof Uploader){
			System.out.println("Uploader");
		}
		System.out.println(gebruikers.get(gebruikersIdSession).getGebruikersNaam()+" het inloggen is gelukt!");
		 */
	}

	void login(){
		boolean validated = false;
		while(validated == false){
			System.out.print("Gebruikersnaam: ");
			String gebruikersnaam = scanner.nextLine();
			System.out.print("Password: ");
			String wachtwoord = scanner.nextLine();
			boolean gelijkeGebruikersnaam = false;
			for(int i=0; i<gebruikers.size(); i++){
				if(gebruikersnaam.equals(gebruikers.get(i).getGebruikersNaam())){
					gelijkeGebruikersnaam = true;
					if(wachtwoord.equals(gebruikers.get(i).getWachtwoord())){
						validated = true;
						gebruikersId = i;
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
	}

	void reviewMenu(){
		String categorie [] = {"Boeken","Muziek","Video's"};

		boolean keuzeGemaakt = false;
		while (keuzeGemaakt == false){
			System.out.println("Kies categorie:");
			System.out.println("===============");
			for (int i = 0; i < categorie.length; i++) {
				System.out.println((i+1)+": "+categorie[i]);
			}
			//optie voor afsluiten of in geval van uploader naar hoofdmenu gaan.
			if(gebruikers.get(gebruikersId) instanceof Reviewer){
				System.out.println("0: Sluit af");
			} else if (gebruikers.get(gebruikersId) instanceof Uploader){
				System.out.println("0: Ga naar hoofdmenu");
			}
			System.out.println();		
			int keuze = 0;
			try {
				keuze = scanner.nextInt();
			} catch (InputMismatchException ime){
				scanner.next();
			}

			switch(keuze){
			case 0:
				if(gebruikers.get(gebruikersId) instanceof Reviewer){
					System.exit(1);
				} else if (gebruikers.get(gebruikersId) instanceof Uploader){
					System.out.println("uploadersmenu");
				}
				break;
			case 1:
				keuzeGemaakt = true;
				boekenLijst();
				break;
			case 2:
				System.out.println("Categorie Muziek");
				keuzeGemaakt = true;
				break;
			case 3:
				System.out.println("Categorie Boeken");
				keuzeGemaakt = true;
				break;
			default:
				System.out.println("Deze keuze zit er niet tussen. Probeer opnieuw...");
				keuzeGemaakt = false;
				break;
			}
		} while (keuzeGemaakt == false);
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


	void valideerInvoer(){
		keuze = -1;
		try {
			keuze = scanner.nextInt();
		} catch (InputMismatchException ime){
			scanner.nextLine();
		}
	}

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
				reviewMenu();
				keuzeGemaakt = true;
			}else if (!keuzeGemaakt){
				System.out.println("Verkeerde invoer. Probeer opnieuw...");
			}
		}
		itemId = boekenlijst[boekKeuze];
		itemMenu();
	}

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
					System.out.println("terug naar Song");
				}else if(items.get(itemId) instanceof Video){
					System.out.println("terug naar Video");
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

	void cijferGeven(){
		int cijfer;
		boolean keuzeGemaakt = false;
		while(keuzeGemaakt == false){
			System.out.println(items.get(itemId).getTitel());
			System.out.println("============================");
			System.out.println("Geef een cijfer van 0 t/m 10:");
			valideerInvoer();
			if(keuze >= 0 && keuze <11){
				cijfer = keuze;
				System.out.println("U heeft het volgende cijfer gegeven: \n"+ cijfer+".0");
				System.out.println("Druk op een toets om verder te gaan...");
				scanner.nextLine();
				items.get(itemId).setCijferLijst((items.get(itemId).getCijferCount()-1), gebruikersId, cijfer);
				gebruikers.get(gebruikersId).setCijferLijst(gebruikers.get(gebruikersId).getCijferCount()-1, itemId, cijfer);
				keuzeGemaakt = true;
			} else {
				System.out.println("Verkeerde invoer. Probeer opnieuw...");
				keuzeGemaakt = false;
			}
		}
	}

}
