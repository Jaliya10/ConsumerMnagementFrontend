package service;

import model.Consumer;
/*import javax.ws.rs.Path;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;*/

import java.sql.Date;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Consumer")
public class ConsumerService {
	Consumer consumerObj = new Consumer();
	
	//retrieve
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readConsumers() { 
		return consumerObj.readConsumers(); 
	} 
	
	
	//insert 
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertConsumer(@FormParam("regNo") String nic, @FormParam("name") String name, @FormParam("address") String address, 
			@FormParam("phone") String phone, @FormParam("regDate") String date /*changed Date to String*/, @FormParam("power_usage") String power, 
			@FormParam("consumer_type") String consumer_type) { 
		String output = consumerObj.insertConsumer(nic, name, address, phone, date, power, consumer_type); 
		return output; 
	}

	//update
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateConsumers(String consumerData) { 
	//Convert the input string to a JSON object 
	 JsonObject consumerObject = new JsonParser().parse(consumerData).getAsJsonObject(); 
	
	 //Read the values from the JSON object
	 String id = consumerObject.get("id").getAsString(); 
	 String nic = consumerObject.get("regNo").getAsString(); 
	 String name = consumerObject.get("name").getAsString(); 
	 String address = consumerObject.get("address").getAsString(); 
	 String phone = consumerObject.get("phone").getAsString(); 
	 String date = consumerObject.get("regDate").getAsString(); 
	 String power = consumerObject.get("power_usage").getAsString(); 
	 String type = consumerObject.get("consumer_type").getAsString(); 
	 String output = consumerObj.updateConsumers(id, nic, name, address, phone, date, power, type); 
	 return output; 
	}
	
	//delete
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteConsumer(String consumerData) { 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(consumerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <id>
	 String id = doc.select("id").text();
	 String output = consumerObj.deleteConsumer(id); 
	 return output;  
	}
		
		//Note use for Inter Service Communication for Getting consumer Data by providing consumer id
		@GET
		@Path("/{compID}")
		@Produces(MediaType.APPLICATION_JSON)
		public String readCompSource(@PathParam("compID") String compID) {		
			JsonObject result = null;
			
			result = consumerObj.readComp(compID);
			return result.toString();		
		}
		
		//http://localhost:8080/ConsumerService/ElectroGrid/Consumer/ConsumerMaintain/3
		//Inter service Communication for Maintenance
		@GET
		@Path("/ConsumerMaintain/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public String readComplaint(@PathParam("id") String id) {		 
			return (new InterServiceCom().Maintenance("/" + id)).toString();
		}	
}
