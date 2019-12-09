package domain;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Say")
public class Says {
	
	@Id
	private String id;
	
	private String input;
	
	private String output;
	
	public Says(String id, String input, String output) {
		this.id = id;
		this.input = input;
		this.output = output;
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
}
