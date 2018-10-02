package model;

public class Information {
	private String fileUpload;
	private String uploadTo;
	private String subFolderName;
	private String email;
	private String firstName;
	private String expected;
	public String getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getUploadTo() {
		return uploadTo;
	}
	public void setUploadTo(String uploadTo) {
		this.uploadTo = uploadTo;
	}
	public String getSubFolderName() {
		return subFolderName;
	}
	public void setSubFolderName(String subFolderName) {
		this.subFolderName = subFolderName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getExpected() {
		return expected;
	}
	public void setExpected(String expected) {
		this.expected = expected;
	}
	@Override
	public String toString() {
		return "Information [fileUpload=" + fileUpload + ", uploadTo=" + uploadTo + ", subFolderName=" + subFolderName
				+ ", email=" + email + ", firstName=" + firstName + ", expected=" + expected + "]";
	}	
}
