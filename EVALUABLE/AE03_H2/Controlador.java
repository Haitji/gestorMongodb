package AE03_H2;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.parser.ParseException;

import com.mongodb.client.model.Filters;
import com.mysql.cj.util.Base64Decoder;

public class Controlador {
	private static Vista vista;
	private static Model model;
	
	public Controlador(Vista vista, Model model) throws Exception {
		this.vista = vista;
		this.model = model;
		InitEventHandler();

	}
	
	public void InitEventHandler() throws Exception {
		vista.btnQuitarFiltro.setVisible(false);
		vista.btnActualizar.setVisible(false);
		 do {
		 
		 }while(!login());

		Bson query = null;
		cargarTabla(query);
		String codigo = (String) vista.table_colection.getValueAt(0, 0);
		int Id = Integer.parseInt(codigo);
		rellenarInfo(Id);
		
		ActionListener agregarDocumento = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verificadorCamposAnyadir()) {
					insertarRegistro();
					limpiarCamposAgregar();
					vista.panel_agregar.setVisible(false);
					try {
						cargarTabla(null);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					limpiarCampos();
				}else {
					JOptionPane.showMessageDialog(null, "Alguns camps son erronis!!","Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		};
		
		ActionListener visualizarPanelAgregar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vista.panel_agregar.setVisible(true);
			}
		};
		
		ActionListener salirPanelAgregar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposAgregar();
				vista.panel_agregar.setVisible(false);
				try {
					cargarTabla(null);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				limpiarCampos();
				model.imgCodificado="";
				String codigo = (String) vista.table_colection.getValueAt(0, 0);
				int Id = Integer.parseInt(codigo);
				rellenarInfo(Id);
			}
		};
		
		ActionListener editarInformacion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = (String) vista.table_colection.getValueAt(vista.table_colection.getSelectedRow(), 0);
				int Id=Integer.parseInt(codigo);
				int opcion = JOptionPane.showConfirmDialog(null,"Estás segur d'actualitçar aquest registre?","Actualitçar Registre", JOptionPane.OK_CANCEL_OPTION);
				if(opcion == JOptionPane.OK_OPTION) {
					if(verificadorCamposEditar()) {
						editarInfo(Id);
						model.imgCodificado=null;
						bloquearControles();
					}else {
						JOptionPane.showMessageDialog(null, "Alguns camps son erronis!!","Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				try {
					cargarTabla(query);
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
			}
		};

		ActionListener elimiarRegistro = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = (String) vista.table_colection.getValueAt(vista.table_colection.getSelectedRow(), 0);
				int Id=Integer.parseInt(codigo);
				int opcion = JOptionPane.showConfirmDialog(null,"Estás segur d'eliminar aquest registre?","Eliminar Registre", JOptionPane.OK_CANCEL_OPTION);
				if(opcion == JOptionPane.OK_OPTION) {
					model.deleteObject(Id);
				}
				try {
					cargarTabla(query);
					codigo = (String) vista.table_colection.getValueAt(0, 0);
					Id = Integer.parseInt(codigo);
					rellenarInfo(Id);
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
			}
		};
		
		MouseListener seleccionarImagen = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e){
				try {
					ImageIcon img=new ImageIcon(model.buscarImagen2());
					Image imgEscalada = img.getImage().getScaledInstance(vista.lblAGima.getWidth(),
							vista.lblAGima.getHeight(), Image.SCALE_SMOOTH);
			        Icon iconoEscalado = new ImageIcon(imgEscalada);
					vista.lblAGima.setIcon(iconoEscalado);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error al carregar la image","Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		};

		MouseListener seleccionarImagen2 = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e){
				try {
					ImageIcon img=new ImageIcon(model.buscarImagen2());
					Image imgEscalada = img.getImage().getScaledInstance(vista.lblImagen.getWidth(),
							vista.lblImagen.getHeight(), Image.SCALE_SMOOTH);
			        Icon iconoEscalado = new ImageIcon(imgEscalada);
					vista.lblImagen.setIcon(iconoEscalado);
					System.out.println(vista.lblImagen.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error al carregar la image","Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					ImageIcon img=new ImageIcon(model.buscarImagen2());
					Image imgEscalada = img.getImage().getScaledInstance(vista.lblImagen.getWidth(),vista.lblImagen.getHeight(), Image.SCALE_SMOOTH);
			        Icon iconoEscalado = new ImageIcon(imgEscalada);
					vista.lblImagen.setIcon(iconoEscalado);
					System.out.println(vista.lblImagen.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error al carregar la image","Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		};
		
		MouseListener recogerIndex = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (vista.table_colection.getSelectedRow() != -1) {
					String codigo = (String) vista.table_colection.getValueAt(vista.table_colection.getSelectedRow(), 0);
					int Id=Integer.parseInt(codigo);
					rellenarInfo(Id);
					bloquearControles();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		};
		
		
		KeyListener filtro= new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtrar=vista.txtFiltro.getText();
				filtro(filtrar);
			}
		};
		
		ActionListener limpiarCampos = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarControles();
				
			}
		};
		
		ActionListener filtroCampos = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Bson query=realizarConsulta();
					cargarTabla(query);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al filtrar","Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		};
		
		ActionListener limpiarFiltro = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bson query = null;
				try {
					cargarTabla(query);
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				vista.btnQuitarFiltro.setVisible(false);
			}
		};
		
		ActionListener eliminarColeccio = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null,"Estás segur d'eliminar aquest la colecció?","Eliminar colecció", JOptionPane.OK_CANCEL_OPTION);
				if(opcion == JOptionPane.OK_OPTION) {
					model.deleteColection();
					vista.table_colection.setModel(new DefaultTableModel());
					vista.lbl_numLibro.setText("");
					vista.lblImagen.setEnabled(false);
					limpiarCampos();
					bloquearBotones();
				}
			}
		};
		
		vista.btnEliminarCol.addActionListener(eliminarColeccio);
		vista.btnAgregar.addActionListener(visualizarPanelAgregar);
		vista.btnBorrar.addActionListener(elimiarRegistro);
		vista.btnActualizar.addActionListener(editarInformacion);
		vista.btnEditar.addActionListener(limpiarCampos);
		vista.table_colection.addMouseListener(recogerIndex);
		vista.btnAGguardar.addActionListener(agregarDocumento);
		vista.btnAGsalir.addActionListener(salirPanelAgregar);
		vista.txtFiltro.addKeyListener(filtro);
		vista.lblAGima.addMouseListener(seleccionarImagen);
		vista.lblImagen.addMouseListener(seleccionarImagen2);
		vista.btnFiltre.addActionListener(filtroCampos);
		vista.btnQuitarFiltro.addActionListener(limpiarFiltro);
	}

	/**
	 * Fa editables els controls
	 */
	public void limpiarControles() {
		vista.lblImagen.setEnabled(true);
		vista.txtNombre.setEditable(true);
		vista.txtAutor.setEditable(true);
		vista.txtFechaN.setEditable(true);
		vista.txtFechaP.setEditable(true);
		vista.txtEditorial.setEditable(true);
		vista.txtNumP.setEditable(true);
		vista.btnActualizar.setVisible(true);
	}
	
	/**
	 * Bloqueja els botons
	 */
	public void bloquearBotones() {
		vista.btnActualizar.setEnabled(false);
		vista.btnBorrar.setEnabled(false);
		vista.btnFiltre.setEnabled(false);
		vista.btnAgregar.setEnabled(false);
		vista.btnEditar.setEnabled(false);
	}
	
	/**
	 * Bloqueja els controls
	 */
	public void bloquearControles() {
		vista.txtNombre.setEditable(false);
		vista.txtAutor.setEditable(false);
		vista.txtFechaN.setEditable(false);
		vista.txtFechaP.setEditable(false);
		vista.txtEditorial.setEditable(false);
		vista.txtNumP.setEditable(false);
		vista.btnActualizar.setVisible(false);
	}

	/**
	 * Limpia tots els camps
	 */
	public void limpiarCampos() {
		vista.txtID.setText("");
		vista.txtNombre.setText("");
		vista.txtAutor.setText("");
		vista.txtFechaN.setText("");
		vista.txtFechaP.setText("");
		vista.txtEditorial.setText("");
		vista.txtNumP.setText("");
		vista.lblImagen.setIcon(null);
	}
	
	/**
	 * Limpia els camps del panel d'agregació
	 */
	public void limpiarCamposAgregar() {
		vista.txtAGid.setText("");
		vista.txtAGtit.setText("");
		vista.txtAGaut.setText("");
		vista.txtAGnax.setText("");
		vista.txtAGpub.setText("");
		vista.txtAGedi.setText("");
		vista.txtAGpag.setText("");
		vista.lblAGima.setIcon(null);
	}

	/**
	 * Recopila l'informació dels camps per a crear un Document que posteriorment pasa a un métode en el model
	 * @param ident Identificador de la llista
	 */
	public void editarInfo(int ident) {
		Document doc = new Document();
		String Thumbnail;
		if(model.imgCodificado.equals("")) {
			Thumbnail=vista.lblImagen.getText();
		}else {
			Thumbnail=model.imgCodificado;
		}
		doc.append("Titulo", vista.txtNombre.getText());
		doc.append("Autor", vista.txtAutor.getText());
		doc.append("Anyo_nacimiento", Integer.parseInt(vista.txtFechaN.getText()));
		doc.append("Anyo_publicacion", Integer.parseInt(vista.txtFechaP.getText()));
		doc.append("Editorial", vista.txtEditorial.getText());
		doc.append("Numero_paginas", Integer.parseInt(vista.txtNumP.getText()));
		
		doc.append("Thumbnail", Thumbnail);

		model.updateObject(ident, doc);
	}

	/**
	 * Replena els camps amb l'informació del registre
	 * @param ident Identificador de la llista
	 */
	public void rellenarInfo(int ident) {
		String info;
		try {
			info = model.mostrarInfoId(ident);
			String[] campos = info.split("\n");

			vista.txtID.setText(campos[0]);
			vista.txtNombre.setText(campos[1]);
			vista.txtAutor.setText(campos[2]);
			vista.txtFechaN.setText(campos[3]);
			vista.txtFechaP.setText(campos[4]);
			vista.txtEditorial.setText(campos[5]);
			vista.txtNumP.setText(campos[6]);
			
			if(campos.length == 7) {
				vista.lblImagen.setText("No image");
				vista.lblImagen.setIcon(null);
			}else if(campos.length == 8){
				vista.lblImagen.setText(campos[7]);
				vista.lblImagen.setIcon(new ImageIcon(crearImage(campos)));
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Decodeja una image en Base64 a un array de bytes
	 * @param imageString String en Base64 de la image
	 * @return Image decodificada en un array de bytes
	 */
	public static byte[] decodeToImage(String imageString) {
		return Base64.getDecoder().decode(imageString);
	}

	/**
	 * Crea una image a partir d'un array de bytes
	 * @param campos Image en un array de bytes
	 * @return Component Image
	 * @throws IOException
	 */
	public Image crearImage(String[] campos) throws IOException {
		byte[] b = decodeToImage(campos[7]);
		return new ImageIcon(b).getImage().getScaledInstance(158, 158, Image.SCALE_SMOOTH);
	}

	/**
	 * Proporciona i comprobar que l'usuari i contrasenya son correctes
	 * @return Boolean true en cas de ser correcte i false en cas contrari
	 * @throws Exception
	 */
	public static boolean login() throws Exception {
		boolean correcte = false;
		JTextField usu = new JTextField();
		JTextField pass = new JTextField();

		Object[] fields = { "Usuari: ", usu, "Contrasenya: ", pass };
		int opcion = JOptionPane.showConfirmDialog(null, fields, "Iniciar sesió", JOptionPane.OK_CANCEL_OPTION);
		if (opcion == JOptionPane.OK_OPTION) {
			String value1 = usu.getText();
			String value2 = pass.getText();
			if (!model.login(value1, value2)) {
				JOptionPane.showMessageDialog(null, "Contrasenya o usuari incorrectes");
			} else {
				JOptionPane.showMessageDialog(null, "Inici de sesió correcte");
				correcte = true;
			}
		} else {
			System.exit(0);
		}
		return correcte;
	}

	/**
	 * Crea un filtre i realitza una consulta de selecció amb aquest
 	 * @return Query de selecció
	 * @throws Exception
	 */
	public Bson realizarConsulta() throws Exception {
		boolean correcte = false;
		Bson query = null;
		int i;
		String[] querys = new String[] { "eq", "gte", "lte" };
		String[] campos = new String[] { "Id","Autor", "Anyo_nacimiento", "Anyo_publicacion","Editorial", "Numero_paginas",  };
		JComboBox tipoCampo = new JComboBox();
		for (i = 0; i < campos.length; i++) {
			tipoCampo.addItem(campos[i]);
		}
		JComboBox tipoQuery = new JComboBox();
		for (i = 0; i < querys.length; i++) {
			tipoQuery.addItem(querys[i]);
		}
		JTextField numero = new JTextField();
		Object[] fields = { "Querys: ", tipoQuery, "Campos: ", tipoCampo, "Valor: ", numero, };
		int opcion = JOptionPane.showConfirmDialog(null, fields, "Iniciar sesió", JOptionPane.OK_CANCEL_OPTION);
		if (opcion == JOptionPane.OK_OPTION) {
			String tquery = (String) tipoQuery.getSelectedItem();
			String campo = (String) tipoCampo.getSelectedItem();
			int resultado = Integer.parseInt(numero.getText());

			switch (tquery) {
			case "eq":
				query = Filters.eq(campo, resultado);
				break;
			case "gte":
				query = Filters.gte(campo, resultado);
				break;
			case "lte":
				query = Filters.lte(campo, resultado);
				break;
			}
			
			vista.btnQuitarFiltro.setVisible(true);
		}
		return query;
	}

	/**
	 * Carrega la taula de buscador
	 * @param query Query de filtre de la taula
	 * @throws IOException
	 * @throws ParseException
	 */
	public void cargarTabla(Bson query) throws IOException, ParseException {
		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		List<Libros> lista = model.mostrarInfo(query);
		for (int i = 0; i < lista.size(); i++) {
			modelo.addRow(new Object[] { String.valueOf(lista.get(i).getId()), lista.get(i).getTitulo() });
		}

		vista.table_colection.setModel(modelo);
		vista.table_colection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumnModel column = vista.table_colection.getColumnModel();
		TableColumn tc = column.getColumn(1);
		tc.setPreferredWidth(300);// Ajustar las columnas al tamaño que mas se adecua

		vista.table_colection.setRowSelectionInterval(0, 0);
		vista.lbl_numLibro.setText(String.valueOf(lista.size()));
	}

	/**
	 * Recopila la informació dels camps creant un objete i inserta un registre en la base de dades 
	 */
	public void insertarRegistro() {
		String Titulo = vista.txtAGtit.getText();
		String Autor = vista.txtAGaut.getText();
		int Anyo_nacimiento = Integer.parseInt(vista.txtAGnax.getText());
		int Anyo_publicacion = Integer.parseInt(vista.txtAGpub.getText());
		String Editorial = vista.txtAGedi.getText();
		int Numero_paginas = Integer.parseInt(vista.txtAGpag.getText());
		String Thumbnail=model.imgCodificado;
		

		Document doc = new Document();
		doc.append("Id", Libros.id_auto + 1);
		doc.append("Titulo", Titulo);
		doc.append("Autor", Autor);
		doc.append("Anyo_nacimiento", Anyo_nacimiento);
		doc.append("Anyo_publicacion", Anyo_publicacion);
		doc.append("Editorial", Editorial);
		doc.append("Numero_paginas", Numero_paginas);
		doc.append("Thumbnail", Thumbnail);
		
		model.insertObject(doc);

	}
	
	/**
	 * Filtre superior de text del buscador
	 * @param filtr Text a buscar
	 */
	public void filtro(String filtr) {
		TableRowSorter<TableModel> t= new TableRowSorter<TableModel>(vista.table_colection.getModel());
		vista.table_colection.setRowSorter(t);
		t.setRowFilter(RowFilter.regexFilter("(?i)"+filtr));

	}

	/**
	 * Verifica que els camps cuan els edites siguen correctes
	 * @return true en cas de correcte true i false en cas contrari
	 */
	public boolean verificadorCamposEditar() {
		int correcto=0;
		if(vista.txtNombre.getText().trim().isEmpty()){
			vista.lblError.setVisible(true);
		}else {
			correcto++;
			vista.lblError.setVisible(false);
		}
		if(vista.txtAutor.getText().trim().isEmpty()){
			vista.lblError_1.setVisible(true);
		}else {
			correcto++;
			vista.lblError_1.setVisible(false);
		}
		if(isNumeric(vista.txtFechaN.getText())) {
			if(vista.txtFechaN.getText().isEmpty()) {
				vista.txtFechaN.setText("0");
				correcto++;
			}else {
				vista.lblError_2.setVisible(true);
			}	
		}else {
			correcto++;
			vista.lblError_2.setVisible(false);
		}
		if(isNumeric(vista.txtFechaP.getText())){
			if(vista.txtFechaP.getText().isEmpty()) {
				vista.txtFechaP.setText("0");
				correcto++;
			}else {
				vista.lblError_3.setVisible(true);
			}
		}else {
			correcto++;
			vista.lblError_3.setVisible(false);
		}
		if(vista.txtEditorial.getText().trim().isEmpty()){
			vista.lblError_4.setVisible(true);
		}else {
			correcto++;
			vista.lblError_4.setVisible(false);
		}
		if(isNumeric(vista.txtNumP.getText())){
			if(vista.txtNumP.getText().isEmpty()) {
				vista.txtNumP.setText("0");
				correcto++;
			}else {
				vista.lblError_5.setVisible(true);
			}
		}else {
			correcto++;
			vista.lblError_5.setVisible(false);
		}
		if(correcto==6) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Verifica que els camps cuan agregues un nou registre siguen correctes
	 * @return true en cas de correcte true i false en cas contrari
	 */
	public boolean verificadorCamposAnyadir() {
		int correcto=0;
		if(vista.txtAGtit.getText().trim().isEmpty()){
			vista.lblError_6.setVisible(true);
		}else {
			correcto++;
			vista.lblError_6.setVisible(false);
		}
		if(vista.txtAGaut.getText().trim().isEmpty()){
			vista.lblError_7.setVisible(true);
		}else {
			correcto++;
			vista.lblError_7.setVisible(false);
		}
		if(isNumeric(vista.txtAGnax.getText())){
			if(vista.txtAGnax.getText().isEmpty()) {
				vista.txtAGnax.setText("0");
				correcto++;
			}else {
				vista.lblError_8.setVisible(true);
			}
		}else {
			correcto++;
			vista.lblError_8.setVisible(false);
		}
		if(isNumeric(vista.txtAGpub.getText())){
			if(vista.txtAGpub.getText().isEmpty()) {
				vista.txtAGpub.setText("0");
				correcto++;
			}else {
				vista.lblError_9.setVisible(true);
			}
		}else {
			correcto++;
			vista.lblError_9.setVisible(false);
		}
		if(vista.txtAGedi.getText().trim().isEmpty()){
			vista.lblError_10.setVisible(true);
		}else {
			correcto++;
			vista.lblError_10.setVisible(false);
		}
		if(isNumeric(vista.txtAGpag.getText())){
			if(vista.txtAGpag.getText().isEmpty()) {
				vista.txtAGpag.setText("0");
				correcto++;
			}else {
				vista.lblError_11.setVisible(true);
			}
		}else {
			correcto++;
			vista.lblError_11.setVisible(false);
		}
		if(correcto==6) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Comprobar que un String es numéric
	 * @param num Numero en String
	 * @return
	 */
	public boolean isNumeric(String num) {
		try{  
			if(num==null) {
				return true;
			}
	          Integer.parseInt(num);
	          return false;
	    }catch(NumberFormatException nfe){
	         return true; 
	    }
	}
	
}
