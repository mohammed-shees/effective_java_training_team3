package in.conceptarchitect.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

	BufferedReader reader;
	
	public Input() {
		reader=new BufferedReader(new InputStreamReader(System.in));
	}	
	
	
	public String readString(String prompt) {
		System.out.print(prompt);
		try {
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	public int readInt(String prompt) {
		String str=readString(prompt);
		return Integer.parseInt(str); //convert a string an integer
	}
	
}
