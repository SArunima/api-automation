package test.API.Automation;
import org.json.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JSONparse {

	public boolean JSONparse_checkResponse()
	{
		Response response = RestAssured.get("https://istheapplestoredown.com/api/v1/status/worldwide");
		String JSONObject = response.asString();
		JSONObject myObject = new JSONObject(JSONObject);
		return checkstatus(myObject);
		
	
	}
	
	public static boolean checkstatus(JSONObject jsonObj) {
	    for (Object key : jsonObj.keySet()) {
	        //based on you key types
	        String keyStr = (String)key;
	        JSONObject keyvalue = (JSONObject) jsonObj.get(keyStr);
	        if(keyvalue.getString("status").equals("y")) {
	        	System.out.println(keyvalue.getString("name").toString()+" has status equal to y");
	        	return false;
	         }
	    }
	    return true;
	}
	
	@Test
	public void checkstatus() {
		Assert.assertEquals( JSONparse_checkResponse(),true);
	}
	
}
