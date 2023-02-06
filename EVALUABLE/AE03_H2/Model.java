package AE03_H2;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.internal.Base64;
import org.json.JSONObject;

import org.json.simple.parser.ParseException;


import com.mongodb.client.MongoCursor;

import com.mongodb.client.model.Filters;



public class Model {

	conexion con = new conexion();
	public static String imgCodificado="";
	
	
	public static void main(String[] args) throws IOException, ParseException {
		Model m=new Model();
		m.con.conexion();
	}
	
	/**
	 * Mostra la informació d'un registre depenent de la seua Id
	 * @param id Identificador del registre
	 * @return String amb tots els camps concatenats
	 * @throws IOException
	 * @throws ParseException
	 */
	public String mostrarInfoId(int id) throws IOException, ParseException {
		con.conexion();
		String info = "";
		Bson query = Filters.eq("Id", id);
		MongoCursor<Document> cursor = con.coleccion1.find(query).iterator();
        while (cursor.hasNext()) {
        	JSONObject obj = new JSONObject(cursor.next().toJson());
        	int nacimiento=0;
        	try {
				nacimiento = obj.getInt("Anyo_nacimiento");
			} catch (Exception e) {
				nacimiento=0;
			}
        	info += (obj.getInt("Id") + "\n" + obj.getString("Titulo") + "\n" + obj.getString("Autor") + "\n" + 
        			nacimiento + "\n" + obj.getInt("Anyo_publicacion") + "\n" + obj.getString("Editorial") + "\n" +
        	obj.getInt("Numero_paginas") + "\n" + obj.getString("Thumbnail"));
        }
        return info;
	}
	
	/**
	 * Borra la colecció de la base de dades
	 */
	public void deleteColection() {
		con.coleccion1.drop();
	}
	
	/**
	 * Realitza una sentencia de borrat sobre un registre de la base de dades
	 * @param id Identificador del registre
	 */
	public void deleteObject(int id) {
		try {
			con.conexion();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		Bson query = Filters.eq("Id", id);
		try {
			con.coleccion1.deleteOne(query);
			JOptionPane.showMessageDialog(null, "Registre eliminat correctament");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el registre");
		}
	}
	
	/**
	 * Realitza una sentencia de actualització sobre un registre de la base de dades
	 * @param id Identificador del registre
	 * @param doc Document que reemplaçará el registre actual
	 */
	public void updateObject(int id, Document doc) {
		try {
			con.conexion();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		Bson query = Filters.eq("Id", id);
		try {
			con.coleccion1.updateOne(query, new Document("$set",doc));
			JOptionPane.showMessageDialog(null, "Camps actualitzats correctament");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Problema al actulitzar els camps");
		}
	}
	
	/**
	 * Realitza una sentencia d'inserció sobre la base de dades
	 * @param doc Document a insertar
	 */
	public void insertObject(Document doc) {
		try {
			con.conexion();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		try {
			con.coleccion1.insertOne(doc);
			JOptionPane.showMessageDialog(null, "Registre insertat correctament");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error al insertar el objecte");
		}
	}
	
	/**
	 * S'encarrega de comprobar que l'usuari i contraseña son correctes
	 * @param user Nom del usuari
	 * @param password Contrasenya de l'usuari
	 * @return Boolean true en cas de ser correcte el login o false en cas contrari
	 * @throws IOException
	 * @throws ParseException
	 */
	public boolean login(String user, String password) throws IOException, ParseException {
		con.conexion();
		boolean continuar = false;
		String pass256 = DigestUtils.sha256Hex(password);
		String us = null, pass = null;
		MongoCursor<Document> cursor = con.coleccion2.find().iterator();
        while (cursor.hasNext()) {
        	JSONObject obj = new JSONObject(cursor.next().toJson());
        	us = obj.getString("user");
        	pass = obj.getString("pass");
            if(user.equals(us) && pass256.equals(pass)) {
            	continuar = true;
            }
        }
        return continuar;
	}
	
	/**
	 * Crea una lista de Llibres completa o filtrada depenent de la query que se li proporcione
	 * @param query Query de filtrat de registres per a la base de dades
	 * @return Retorna una lista de llibres carregada
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<Libros> mostrarInfo(Bson query) throws IOException, ParseException {
        con.conexion();
        List<Libros> lista= new ArrayList<Libros>();
        if(query == null) {
            MongoCursor<Document> cursor = con.coleccion1.find().iterator();
            while (cursor.hasNext()) {
                JSONObject obj = new JSONObject(cursor.next().toJson());
                int id=obj.getInt("Id");
                String titulo=obj.getString("Titulo");
                lista.add(new Libros(id,titulo));
            }
            return lista;
        }else {
            MongoCursor<Document> cursor = con.coleccion1.find(query).iterator();
            while (cursor.hasNext()) {
                JSONObject obj = new JSONObject(cursor.next().toJson());
                int id=obj.getInt("Id");
                String titulo=obj.getString("Titulo");
                lista.add(new Libros(id,titulo));
            }
            return lista;
        }
    }
	
	/**
	 * Obri un buscador de directoris on podrás seleccionar una image a carregar
	 * @return Image codificada a Base64
	 * @throws Exception
	 */
	public String buscarImagen2() throws Exception {
		 JFileChooser selector=new JFileChooser();
		    FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
		    selector.setFileFilter(filtroImagen);
		    int r=selector.showOpenDialog(null);
		    if(r==JFileChooser.APPROVE_OPTION){
		     File f=selector.getSelectedFile();
		      System.out.println(f.getPath());
		   }
		    codificar(selector.getSelectedFile().getPath());
		    return selector.getSelectedFile().getPath();
	}

	/**
	 * Codifica una Image a un array de bytes per a despres codificarla a Base64
	 * @param url Direcció de la image
	 * @throws Exception
	 */
	public void codificar(String url) throws Exception {
		byte[] buffer = loadImage64(url);
		String encodedString = Base64.encode(buffer);
		imgCodificado=encodedString;
	}
	
	
	/**
	 * Carrega una image i retorna el seu valor en un array de bytes
	 * @param url Direcció de la image
	 * @return Image guardada en un array de bytes
	 * @throws Exception
	 */
	public byte[] loadImage64(String url)throws Exception{
	    File file= new File(url.toString());
	    if(file.exists()){
	        int lenght = (int)file.length();
	        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
	        byte[] bytes = new byte[lenght];
	        reader.read(bytes, 0, lenght);
	        reader.close();
	        return bytes;
	    }else{
	    	System.out.println("Recurso no encontrado");
	        return null;
	    }
	}
	


}
