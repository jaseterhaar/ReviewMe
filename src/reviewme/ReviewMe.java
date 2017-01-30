package reviewme;

import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class ReviewMe {
	static Scanner scanner = new Scanner(System.in);
	static ArrayList <Gebruiker> gebruikers = new ArrayList<>(); 
	static ArrayList <Upload> uploads = new ArrayList<>();
	static int gebruikersIdSession = 2;
	static int uploadCount;
	private int keuze;
	private int uploadId;


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

	//Categorie keuzes
	void boekenLijst(){
		boolean keuzeGemaakt = false;
		do{	
			System.out.println("Welk boek wilt u beoordelen?");
			System.out.println("============================");

			ArrayList <Integer> bookId = new ArrayList<>();

			for (int i = 0; i < uploadCount; i++) {

				if(uploads.get(i) instanceof Boek){
					bookId.add(i);
					System.out.println((bookId.size())+": "+uploads.get(i).getTitel());				
				}
			}
			System.out.println("0: Ga terug naar het vorige menu");
			//////////////
			valideerInvoer();
			//////////////
			keuze = keuze-1;
			for (int i = 0; i < bookId.size(); i++) {
				if(keuze == i){
					uploadId = bookId.get(i);
					System.out.println();
					keuzeGemaakt = true;
				}
			}
			if (keuze >= bookId.size() || keuze < -1){
				System.out.println("De ingevoerde keuze is onbekend. Probeer opnieuw ... ");
			} else if (keuze == -1){
				reviewMenu();
			}
		} while (keuzeGemaakt == false);

		keuzeGemaakt = false;
		while(keuzeGemaakt == false){
			System.out.println("========================================");
			System.out.println("U heeft gekozen voor:\n"+uploads.get(uploadId).titel);
			System.out.println("----------------------------------------");
			System.out.println("Maak een keuze:");
			System.out.println("1: Geef cijfer\n2: Meer info\n3: Ga naar vorig menu");
			valideerInvoer();
			switch(keuze){
			case 1:
				geefCijfer();
				keuzeGemaakt = true;
				break;
			case 2:
				System.out.println("geef meer info");
				keuzeGemaakt = true;
				break;
			case 3:
				boekenLijst();
				break;
			default:
				System.out.println("Deze keuze zit er niet tussen. Probeer opnieuw...");
				keuzeGemaakt = false;
				break;
			}
		}

	}
	
	void geefCijfer(){
		System.out.println(uploads.get(uploadId).titel);
		//Kijken of het cijfer al gegeven is
		gebruikers.get(2).setUploadIdCijfer(2);//0
		gebruikers.get(2).setGegevenCijfer(0, 8);//0
		gebruikers.get(2).setUploadIdCijfer(0);//1
		gebruikers.get(2).setGegevenCijfer(1, 6);//1
		boolean lijstNietLeeg = false;
		System.out.println(gebruikers.get(gebruikersIdSession).getUploadIdCijfer(1));
		
		for(int i = 0; i < gebruikers.get(gebruikersIdSession).getUploadIdCijfer().size(); i++){
			if(uploadId == gebruikers.get(gebruikersIdSession).getUploadIdCijfer(i)){
				System.out.println("true " + i);
			}
		}
//		if(!lijstNietLeeg){
//			System.out.println("Lijst leeg");
//		}
		System.out.println("Geef een cijfer tussen 0 - 10");
	}

	void valideerInvoer(){
		keuze = -1;
		try {
			keuze = scanner.nextInt();
		} catch (InputMismatchException ime){
			scanner.nextLine();
		}
	}


}
