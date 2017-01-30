package reviewme;

import java.util.ArrayList;

public abstract class Gebruiker {
	String gebruikersNaam;
	String wachtwoord;
	
	private ArrayList <Integer> uploadIdCijfer = new ArrayList<>();
	private ArrayList <Integer> gegevenCijfer = new ArrayList<>();
	
	public String getGebruikersNaam(){
		return gebruikersNaam;
	}
	public void setGebruikersNaam(String naam){
		gebruikersNaam = naam;
	}
	
	public String getWachtwoord(){
		return wachtwoord;
	}
	public void setWachtwoord(String password){
		wachtwoord = password;
	}
	
	public ArrayList getUploadIdCijfer(){
		return uploadIdCijfer;
	}
	public Integer getUploadIdCijfer(int index){
		return uploadIdCijfer.get(index);
	}
	public void setUploadIdCijfer(int uploadId){
		//boolean cijferGegeven = false;
		//for (int i = 0; i < uploadIdCijfer.size(); i++) {
		//	if(i == uploadIdCijfer.get(i)){
		//		cijferGegeven = true;
		//	}
		//}
		//if(cijferGegeven == false){
			uploadIdCijfer.add(uploadId);
		//}		
	}

	
	public ArrayList getGegevenCijfer(){
		return gegevenCijfer;
	}	
	public void setGegevenCijfer(int index, int cijfer){
		gegevenCijfer.add(index,cijfer);
	}
}
