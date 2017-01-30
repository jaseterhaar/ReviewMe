package reviewme;

public abstract class Item {
	private int uploadId;
	private String titel;
	private int cijferCount=1;
	
	private int [][] cijferLijst = new int[cijferCount][2];
	
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

	public int [][]getCijferLijst(){
		return cijferLijst;
	}
	public void setCijferLijst(int index, int gebruikersId, int cijfer){
		cijferLijst[index][0] = gebruikersId;
		cijferLijst[index][1] = cijfer;
		cijferCount++;
	}
	
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
