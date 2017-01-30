package reviewme;

import java.util.ArrayList;

public abstract class Gebruiker {
	String gebruikersNaam;
	String wachtwoord;
	int cijferCount=1;
	
	//[cijferId][cijfer]
	private int [][] cijferLijst = new int[cijferCount][2];
	
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
	
	public int [][]getCijferLijst(){
		return cijferLijst;
	}
	public void setCijferLijst(int index, int cijferId, int cijfer){
		cijferLijst[index][0] = cijferId;
		cijferLijst[index][1] = cijfer;
		cijferCount++;
	}
	public int getCijferCount(){
		return cijferCount;
	}
	
}
