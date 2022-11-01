package com.reqres.utilities;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReading {

	public String getJSONData(String key, String JSONFilePath) {
		String jsonData = "";
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(JSONFilePath));
			//Typecast obj to JSONObject
			JSONObject jsonObject = (JSONObject) obj;
			//getting First name and last name
			jsonData = (String) jsonObject.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonData;
	}

//	public static void main(String[] args) {
//		JSONReading obj = new JSONReading();
//		try {
//			System.out.println(obj.getJSONData("name",
//					"D:/APIAutomatiom/APIAutomationFramework/src/test/java/com/reqres/utilities/CreateUserData.json"));
//		} catch (Exception e) {
//			System.out.println("Not data found" + e);
//		}
//	}

}
