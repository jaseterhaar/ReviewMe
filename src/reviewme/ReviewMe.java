package reviewme;

import java.util.*;

public class ReviewMe {
	static Scanner scanner = new Scanner(System.in);
	static ArrayList <Gebruiker> gebruikers = new ArrayList<>(); 
	static ArrayList <Upload> uploads = new ArrayList<>();
	static int gebruikersIdSession = 2;
	static int uploadCount;
	private int keuze;


	void start(){		
		laadGebruikers(); //inladen van gebruikers
		laadUploads(); //inladen van uploads
		beoordeling();

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
						gebruikersIdSession = i;
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
			if(gebruikers.get(gebruikersIdSession) instanceof Reviewer){
				System.out.println("0: Sluit af");
			} else if (gebruikers.get(gebruikersIdSession) instanceof Uploader){
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
				if(gebruikers.get(gebruikersIdSession) instanceof Reviewer){
					System.exit(1);
				} else if (gebruikers.get(gebruikersIdSession) instanceof Uploader){
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
		uploads.add((Boek)new Boek(0,"Wetten der Magie"));
		uploads.add((Boek)new Boek(1,"Yab Yum"));
		uploads.add((Boek)new Boek(2,"Het testament"));
		uploads.add((Song)new Song(3,"Brindis"));
		uploads.add((Song)new Song(4,"Hold up"));
		uploads.add((Song)new Song(5,"Bohemian Rhapsody"));
		uploads.add((Video)new Video(6,"Card flourishes"));
		uploads.add((Video)new Video(7,"Shawshank Redemption"));
		uploads.add((Video)new Video(8,"How to make cocktails"));
		uploads.add((Boek)new Boek(9,"Max Havelaar"));

		uploadCount = uploads.size();

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
		for(int i=0; i < uploads.size(); i++){
			if(uploads.get(i) instanceof Boek){
				aantalBoeken++;
			}
		}
		int [] boekenlijst = new int[aantalBoeken];
		boolean keuzeGemaakt = false;
		while(keuzeGemaakt == false){
			System.out.println("Kies een boek:");
			System.out.println("=======================");
			int index = 0;
			for (int i = 0; i < uploads.size(); i++) {
				if(uploads.get(i) instanceof Boek){
					System.out.println((index+1)+" : "+ uploads.get(i).getTitel());
					boekenlijst[index] = i;
					index++;
				}
			}
			System.out.println("0 : Terug naar vorig menu");
			valideerInvoer();
			for (int i = 0; i < boekenlijst.length; i++) {
				if(keuze == (i+1)){
					keuzeGemaakt = true;
				}
			}
			if(keuze == 0){
				reviewMenu();
				keuzeGemaakt = true;
			}else if (!keuzeGemaakt){
				System.out.println("Verkeerde invoer. Probeer opnieuw...");
			}
		}
		beoordeling();
	}
	
	void beoordeling(){
		keuze = 0;
		
	}

}
