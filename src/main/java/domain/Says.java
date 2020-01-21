package domain;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Say")
public class Says {
	
	@Id
	private String id;
	
	private String input;
	
	private String output;
	
	private String imgurl;
	
	private String url;
	
	public Says(String id, String input, String output, String imgurl, String url) {
		this.id = id;
		this.input = input;
		this.output = output;
		this.imgurl = imgurl;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	@Override
	public String toString() {
		return output;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
