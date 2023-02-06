package AE03_H2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class conexion {
	public static MongoClient mongoClient;
	public static MongoDatabase database;
	public MongoCollection<Document> coleccion1;
	public MongoCollection<Document> coleccion2;
	
	public void conexion() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("conexion.json");
        Object obj = parser.parse(reader);
        JSONObject pJsonObj = (JSONObject)obj;
            
        String ip = (String) pJsonObj.get("ip");
        Long port = (Long) pJsonObj.get("port");
        String databaseS = (String) pJsonObj.get("database");
        JSONArray collections = (JSONArray)pJsonObj.get("collections");
        
        int portR = port.intValue();
        
    	mongoClient = new MongoClient(ip, portR);
    	database = mongoClient.getDatabase(databaseS);

    	JSONObject coleccioneSel = (JSONObject)collections.get(0);
    	String nameColeccion = (String) coleccioneSel.get("collection");
    	coleccion1 = database.getCollection(nameColeccion);
    	
    	JSONObject coleccioneSel1 = (JSONObject)collections.get(1);
    	String nameColeccion1 = (String) coleccioneSel1.get("collection");
    	coleccion2 = database.getCollection(nameColeccion1);
	}
}

