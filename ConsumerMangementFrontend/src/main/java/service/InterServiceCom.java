package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class InterServiceCom {
	private static final String PROTOCOL = "http://";
	private static final String HOST = "127.0.0.1";
	private static final String PORT = "8080";
	private static final String MAINTENANCE_SERVICE_URI = PROTOCOL + HOST + ":" + PORT + "/MaintenanceService/ElectroGrid/Maintenance";
	private static final String SERVICE_TOKEN="Basic dXNlcjpwYXNzd29yZA==";

public JsonObject Maintenance(String path) {
		Client client = Client.create();

		WebResource webResource = client.resource(MAINTENANCE_SERVICE_URI+path);
		String output=webResource.header("Authorization", SERVICE_TOKEN).get(String.class);
		
		JsonObject JSONoutput = new JsonParser().parse(output).getAsJsonObject();
		return JSONoutput;
	  }
}
