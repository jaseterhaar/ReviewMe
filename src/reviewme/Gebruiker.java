package reviewme;

import java.util.ArrayList;

public abstract class Gebruiker {
	private String gebruikersNaam;
	private String wachtwoord;
	private int cijferCount=1;
	

	private ArrayList<Integer> itemId = new ArrayList<>();
	private ArrayList<Integer> cijferlijst = new ArrayList<>();
	
	
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
	
	//lijsten voor de gegeven cijfers en methodes voor het aanmaken van deze waarden
	public ArrayList getItemId(){
		return itemId;
	}
	public ArrayList getCijferlijst(){
		return cijferlijst;
	}
	public void addCijfer(int idOfItem, int cijfer){
		itemId.add(idOfItem);
		cijferlijst.add(cijfer);	
	}
	public void changeCijfer(int index, int itemId, int cijfer){
		
		this.itemId.set(index, itemId);
		cijferlijst.set(index, cijfer);	
	}
	
	public int getCijferCount(){
		return cijferCount;
	}
	
}
