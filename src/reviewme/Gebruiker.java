package reviewme;

import java.util.ArrayList;

public abstract class Gebruiker {
	String gebruikersNaam;
	String wachtwoord;
	int aantalBeoordelingen;
	
	//[cijferId][cijfer]
	private int [][] cijferlijst = new int[aantalBeoordelingen][];
	
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
	
	public int[][] getCijferlijst(){
		return cijferlijst;
	}
	public void setCijferlijst(int index, int cijferId, int cijfer){
		cijferlijst[index][index] = cijferId;
		cijferlijst[index][index+1] = cijfer;
	}
	
	
}
