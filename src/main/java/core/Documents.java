package core;

public class Documents {
	private int documents_id;
	private String documents_name;
	private String documents_description;
	private String documents_image;
	
	public Documents(){}
	
	public Documents(int docId, String docName, String docDescription, String docImg) {
        this.setDocuments_id(docId);
        this.setDocuments_name(docName);
        this.setDocuments_description(docDescription);
        this.setDocuments_image(docImg);
	}
	//Getter and Setter
	public int getDocuments_id() {
		return documents_id;
	}

	public void setDocuments_id(int documents_id) {
		this.documents_id = documents_id;
	}

	public String getDocuments_description() {
		return documents_description;
	}

	public void setDocuments_description(String documents_description) {
		this.documents_description = documents_description;
	}

	public String getDocuments_image() {
		return documents_image;
	}

	public void setDocuments_image(String documents_image) {
		this.documents_image = documents_image;
	}

	public String getDocuments_name() {
		return documents_name;
	}

	public void setDocuments_name(String documents_name) {
		this.documents_name = documents_name;
	}
}
