package reviewme;

public abstract class Upload {
	int uploadId;
	String titel;
	int cijfer;
	
	int getUploadId(){
		return uploadId;
	}
	void setUploadId(int id){
		uploadId = id;
	}
	
	String getTitel(){
		return titel;
	}	
	void setTitel(String title){
		titel = title;
	}
	
}

class Boek extends Upload{
	Boek(int id, String titel){
		setUploadId(id);
		setTitel(titel);
	}
	
}

class Song extends Upload{
	Song(int id, String titel){
		setUploadId(id);
		setTitel(titel);
	}
	
}

class Video extends Upload{
	Video(int id, String titel){
		setUploadId(id);
		setTitel(titel);
	}
	
}
