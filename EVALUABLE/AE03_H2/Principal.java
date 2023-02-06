package AE03_H2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Principal {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	    
		Model model = new Model();
		Vista vista = new Vista();

		Controlador controlador = new Controlador(vista, model);
	}
}
