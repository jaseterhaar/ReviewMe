package reviewme;

import java.util.ArrayList;

public abstract class Item {
	private int uploadId;
	private String titel;
	private int cijferCount=1;

	private ArrayList<Integer> gebruikerId = new ArrayList<>();
	private ArrayList<Integer> cijferlijst = new ArrayList<>();
	

	public int getUploadId(){
		return uploadId;
	}
	public void setUploadId(int id){
		uploadId = id;
	}

	public String getTitel(){
		return titel;
	}	
	public void setTitel(String title){
		titel = title;
	}

	public ArrayList getGebruikerId(){
		return gebruikerId;
	}
	public ArrayList getCijferlijst(){
		return cijferlijst;
	}
	public void addCijfer(int itemId, int cijfer){
		
		this.gebruikerId.add(itemId);
		cijferlijst.add(cijfer);
		
	}
	public void changeCijfer(int index, int itemId, int cijfer){
		System.out.println("index" + index);
		System.out.println("itemId" + itemId);
		System.out.println("cijfer" + cijfer);
		
		this.gebruikerId.set(index, itemId);
		cijferlijst.set(index, cijfer);	
	}

	//aantal gegeven cijfers bij item
	public int getCijferCount(){
		return cijferCount;
	}

}

class Boek extends Item{
	Boek(int id, String titel){
		setUploadId(id);
		setTitel(titel);
	}

}

class Song extends Item{
	Song(int id, String titel){
		setUploadId(id);
		setTitel(titel);
	}

}

class Video extends Item{
	Video(int id, String titel){
		setUploadId(id);
		setTitel(titel);
	}

}
