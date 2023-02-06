package AE03_H2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;


public class Vista extends JFrame {

	JFrame frame;
	JPanel contentPane;
	JTable table_colection;
	JTextField txtEditorial;
	JTextField txtFechaP;
	JTextField txtFechaN;
	JTextField txtAutor;
	JTextField txtNombre;
	JTextField txtID;
	JTextField textField;
	JTextField txtNumP;
	JLabel lblImagen;
	JButton btnAgregar;
	JButton btnEditar;
	JButton btnBorrar;
	JButton btnActualizar;
	JLabel lbl_numLibro;
	JPanel panel_agregar;
	private JLabel lblNewLabel_1_6;
	JLabel lblAGima;
	private JLabel lblNewLabel_1_2_2;
	JTextField txtAGedi;
	private JLabel lblNewLabel_1_7;
	private JLabel lblNewLabel_1_8;
	JTextField txtAGpub;
	JTextField txtAGnax;
	private JLabel lblNewLabel_1_3_2;
	private JLabel lblNewLabel_1_9;
	JTextField txtAGaut;
	JTextField txtAGtit;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1_10;
	JTextField txtAGid;
	JTextField txtAGpag;
	private JLabel lblAgregarLlibre;
	JButton btnAGguardar;
	JButton btnAGsalir;
	JTextField txtFiltro;
	private JPanel panel_1;
	private JTabbedPane tabbedPane;
	JButton btnFiltre;
	JButton btnQuitarFiltro;
	JButton btnEliminarCol;
	
	//Error labels
		//===================================
		JLabel lblError;
		JLabel lblError_1;
		JLabel lblError_2;
		JLabel lblError_3;
		JLabel lblError_4;
		JLabel lblError_5;
		JLabel lblError_6;
		JLabel lblError_7;
		JLabel lblError_8;
		JLabel lblError_9;
		JLabel lblError_10;
		JLabel lblError_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Vista a=new Vista();
		a.panel_agregar.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	
	public void aaa() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 911, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);
		frame.getContentPane().add(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 895, 593);
		contentPane.add(tabbedPane);
				
			panel_1 = new JPanel();
			tabbedPane.addTab("Llibres", null, panel_1, null);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Total llibres:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setBounds(33, 433, 134, 45);
			panel_1.add(lblNewLabel);
			
			lbl_numLibro = new JLabel("0");
			lbl_numLibro.setFont(new Font("Tahoma", Font.BOLD, 18));
			lbl_numLibro.setBounds(183, 433, 66, 45);
			panel_1.add(lbl_numLibro);
			
			JLabel lblListaDeColeccin = new JLabel("Llista de coleccions");
			lblListaDeColeccin.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblListaDeColeccin.setBounds(33, 10, 175, 45);
			panel_1.add(lblListaDeColeccin);
			
			btnAgregar = new JButton("Agregar");
			btnAgregar.setBackground(Color.LIGHT_GRAY);
			btnAgregar.setBounds(726, 60, 129, 45);
			panel_1.add(btnAgregar);
			
			btnEditar = new JButton("Editar");
			btnEditar.setBackground(Color.LIGHT_GRAY);
			btnEditar.setBounds(726, 116, 129, 45);
			panel_1.add(btnEditar);
			
			btnBorrar = new JButton("Esborrar");
			btnBorrar.setBackground(Color.LIGHT_GRAY);
			btnBorrar.setBounds(726, 172, 129, 45);
			panel_1.add(btnBorrar);
			
			btnActualizar = new JButton("Actulitzar");
			btnActualizar.setBackground(Color.LIGHT_GRAY);
			btnActualizar.setBounds(726, 480, 129, 45);
			panel_1.add(btnActualizar);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(33, 65, 283, 358);
			panel_1.add(scrollPane);
			
			table_colection = new JTable();
			scrollPane.setViewportView(table_colection);
			
			panel_agregar = new JPanel();
			panel_agregar.setBackground(Color.LIGHT_GRAY);
			panel_agregar.setLayout(null);
			panel_agregar.setBounds(326, 21, 360, 504);
			panel_1.add(panel_agregar);
			
			lblNewLabel_1_6 = new JLabel("Image");
			lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1_6.setBounds(22, 334, 66, 28);
			panel_agregar.add(lblNewLabel_1_6);
			
			lblAGima = new JLabel("");
			
			
			lblAGima.setOpaque(true);
			lblAGima.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAGima.setBackground(SystemColor.activeCaption);
			lblAGima.setBounds(157, 336, 158, 158);
			panel_agregar.add(lblAGima);
			
			lblNewLabel_1_2_2 = new JLabel("Nombre págines");
			lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1_2_2.setBounds(22, 282, 141, 28);
			panel_agregar.add(lblNewLabel_1_2_2);
			
			txtAGedi = new JTextField();
			txtAGedi.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtAGedi.setColumns(10);
			txtAGedi.setBounds(86, 244, 229, 28);
			panel_agregar.add(txtAGedi);
			
			lblNewLabel_1_7 = new JLabel("Editorial");
			lblNewLabel_1_7.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1_7.setBounds(22, 244, 66, 28);
			panel_agregar.add(lblNewLabel_1_7);
			
			lblNewLabel_1_8 = new JLabel("Data publicació");
			lblNewLabel_1_8.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1_8.setBounds(22, 206, 141, 28);
			panel_agregar.add(lblNewLabel_1_8);
			
			txtAGpub = new JTextField();
			txtAGpub.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtAGpub.setColumns(10);
			txtAGpub.setBounds(173, 206, 142, 28);
			panel_agregar.add(txtAGpub);
			
			txtAGnax = new JTextField();
			txtAGnax.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtAGnax.setColumns(10);
			txtAGnax.setBounds(173, 168, 142, 28);
			panel_agregar.add(txtAGnax);
			
			lblNewLabel_1_3_2 = new JLabel("Data naixement");
			lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1_3_2.setBounds(22, 168, 141, 28);
			panel_agregar.add(lblNewLabel_1_3_2);
			
			lblNewLabel_1_9 = new JLabel("Autor");
			lblNewLabel_1_9.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1_9.setBounds(22, 130, 66, 28);
			panel_agregar.add(lblNewLabel_1_9);
			
			txtAGaut = new JTextField();
			txtAGaut.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtAGaut.setColumns(10);
			txtAGaut.setBounds(86, 130, 229, 28);
			panel_agregar.add(txtAGaut);
			
			txtAGtit = new JTextField();
			txtAGtit.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtAGtit.setColumns(10);
			txtAGtit.setBounds(86, 92, 229, 28);
			panel_agregar.add(txtAGtit);
			
			lblNewLabel_2 = new JLabel("Titol");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_2.setBounds(22, 92, 66, 28);
			panel_agregar.add(lblNewLabel_2);
			
			lblNewLabel_1_10 = new JLabel("ID");
			lblNewLabel_1_10.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1_10.setBounds(22, 53, 66, 28);
			panel_agregar.add(lblNewLabel_1_10);
			
			txtAGid = new JTextField();
			txtAGid.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtAGid.setEditable(false);
			txtAGid.setColumns(10);
			txtAGid.setBounds(86, 54, 229, 28);
			panel_agregar.add(txtAGid);
			
			txtAGpag = new JTextField();
			txtAGpag.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtAGpag.setColumns(10);
			txtAGpag.setBounds(173, 283, 142, 28);
			panel_agregar.add(txtAGpag);
			
			lblAgregarLlibre = new JLabel("Agregar llibre");
			lblAgregarLlibre.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblAgregarLlibre.setBounds(10, 0, 165, 45);
			panel_agregar.add(lblAgregarLlibre);
			
			btnAGguardar = new JButton("Guardar");
			btnAGguardar.setBounds(22, 457, 110, 37);
			panel_agregar.add(btnAGguardar);
			
			btnAGsalir = new JButton("X");
			btnAGsalir.setForeground(Color.RED);
			btnAGsalir.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnAGsalir.setBounds(317, 0, 43, 37);
			//		ImageIcon icon = new ImageIcon("./Recursos/cerca.png");
			//		Image imgEscalada = icon.getImage().getScaledInstance(btnAGsalir.getWidth(),btnAGsalir.getHeight(), Image.SCALE_SMOOTH);
			//        Icon iconoEscalado = new ImageIcon(imgEscalada);
			//        btnAGsalir.setIcon(iconoEscalado);
					panel_agregar.add(btnAGsalir);
					
					
					JPanel panel = new JPanel();
					panel.setBounds(326, 21, 360, 504);
					panel_1.add(panel);
					panel.setLayout(null);
					
					JLabel lblNewLabel_1_4 = new JLabel("Image");
					lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_1_4.setBounds(41, 336, 66, 28);
					panel.add(lblNewLabel_1_4);
					
					lblImagen = new JLabel("Elegir imagen");
					lblImagen.setOpaque(true);
					lblImagen.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblImagen.setBackground(SystemColor.activeCaption);
					lblImagen.setBounds(157, 336, 158, 158);
					panel.add(lblImagen);
					
					JLabel lblNewLabel_1_2_1 = new JLabel("Nombre págines");
					lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_1_2_1.setBounds(22, 282, 141, 28);
					panel.add(lblNewLabel_1_2_1);
					
					txtEditorial = new JTextField();
					txtEditorial.setEditable(false);
					txtEditorial.setFont(new Font("Tahoma", Font.BOLD, 14));
					txtEditorial.setColumns(10);
					txtEditorial.setBounds(86, 244, 229, 28);
					panel.add(txtEditorial);
					
					JLabel lblNewLabel_1_2 = new JLabel("Editorial");
					lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_1_2.setBounds(22, 244, 66, 28);
					panel.add(lblNewLabel_1_2);
					
					JLabel lblNewLabel_1_3 = new JLabel("Data publicació");
					lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_1_3.setBounds(22, 206, 141, 28);
					panel.add(lblNewLabel_1_3);
					
					txtFechaP = new JTextField();
					txtFechaP.setEditable(false);
					txtFechaP.setFont(new Font("Tahoma", Font.BOLD, 14));
					txtFechaP.setColumns(10);
					txtFechaP.setBounds(173, 206, 142, 28);
					panel.add(txtFechaP);
					
					txtFechaN = new JTextField();
					txtFechaN.setEditable(false);
					txtFechaN.setFont(new Font("Tahoma", Font.BOLD, 14));
					txtFechaN.setColumns(10);
					txtFechaN.setBounds(173, 168, 142, 28);
					panel.add(txtFechaN);
					
					JLabel lblNewLabel_1_3_1 = new JLabel("Data naixement");
					lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_1_3_1.setBounds(22, 168, 141, 28);
					panel.add(lblNewLabel_1_3_1);
					
					JLabel lblNewLabel_1_1 = new JLabel("Autor");
					lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_1_1.setBounds(22, 130, 66, 28);
					panel.add(lblNewLabel_1_1);
					
					txtAutor = new JTextField();
					txtAutor.setEditable(false);
					txtAutor.setFont(new Font("Tahoma", Font.BOLD, 14));
					txtAutor.setColumns(10);
					txtAutor.setBounds(86, 130, 229, 28);
					panel.add(txtAutor);
					
					txtNombre = new JTextField();
					txtNombre.setEditable(false);
					txtNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
					txtNombre.setColumns(10);
					txtNombre.setBounds(86, 92, 229, 28);
					panel.add(txtNombre);
					
					JLabel lblNewLabel_1 = new JLabel("Titol");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_1.setBounds(22, 92, 66, 28);
					panel.add(lblNewLabel_1);
					
					JLabel lblNewLabel_1_5 = new JLabel("ID");
					lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_1_5.setBounds(22, 53, 50, 28);
					panel.add(lblNewLabel_1_5);
					
					txtID = new JTextField();
					txtID.setEditable(false);
					txtID.setFont(new Font("Tahoma", Font.BOLD, 14));
					txtID.setColumns(10);
					txtID.setBounds(86, 54, 229, 28);
					panel.add(txtID);
					
					txtNumP = new JTextField();
					txtNumP.setEditable(false);
					txtNumP.setFont(new Font("Tahoma", Font.BOLD, 14));
					txtNumP.setColumns(10);
					txtNumP.setBounds(173, 283, 142, 28);
					panel.add(txtNumP);
					
					JLabel lblCaracteristicas = new JLabel("Caracterísitques:");
					lblCaracteristicas.setBounds(10, 0, 165, 45);
					panel.add(lblCaracteristicas);
					lblCaracteristicas.setFont(new Font("Tahoma", Font.BOLD, 18));
					
					txtFiltro = new JTextField();	
					txtFiltro.setToolTipText("Filtro");
					txtFiltro.setBounds(33, 47, 283, 19);
					panel_1.add(txtFiltro);
					txtFiltro.setColumns(10);
					TextPrompt placeholder = new TextPrompt("Buscar per nom", txtFiltro);
					placeholder.changeAlpha(0.75f);
				    placeholder.changeStyle(Font.ITALIC);
					
					//=================================================================================
					
					lblError = new JLabel("X");
					lblError.setToolTipText("Titol no port estar buid");
					lblError.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError.setHorizontalAlignment(SwingConstants.CENTER);
					lblError.setForeground(Color.RED);
					lblError.setBounds(315, 92, 25, 28);
					panel.add(lblError);
					
					lblError_1 = new JLabel("X");
					lblError_1.setToolTipText("Autor no pot estar buid");
					lblError_1.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_1.setForeground(Color.RED);
					lblError_1.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_1.setBounds(315, 130, 25, 28);
					panel.add(lblError_1);
					
					lblError_2 = new JLabel("X");
					lblError_2.setToolTipText("Data naixement te que ser numeric");
					lblError_2.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_2.setForeground(Color.RED);
					lblError_2.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_2.setBounds(315, 168, 25, 28);
					panel.add(lblError_2);
					
					lblError_3 = new JLabel("X");
					lblError_3.setToolTipText("Data publicacio te que ser numeric");
					lblError_3.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_3.setForeground(Color.RED);
					lblError_3.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_3.setBounds(315, 206, 25, 28);
					panel.add(lblError_3);
					
					lblError_4 = new JLabel("X");
					lblError_4.setToolTipText("Editorial no pot estar buid");
					lblError_4.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_4.setForeground(Color.RED);
					lblError_4.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_4.setBounds(315, 244, 25, 28);
					panel.add(lblError_4);
					
					lblError_5 = new JLabel("X");
					lblError_5.setToolTipText("Nom de pagines te que ser numeric");
					lblError_5.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_5.setForeground(Color.RED);
					lblError_5.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_5.setBounds(315, 282, 25, 28);
					panel.add(lblError_5);
					
					lblError_6 = new JLabel("X");
					lblError_6.setToolTipText("Titol no port estar buid");
					lblError_6.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_6.setForeground(Color.RED);
					lblError_6.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_6.setBounds(311, 92, 25, 28);
					panel_agregar.add(lblError_6);
					
					lblError_7 = new JLabel("X");
					lblError_7.setToolTipText("Autor no pot estar buid");
					lblError_7.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_7.setForeground(Color.RED);
					lblError_7.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_7.setBounds(311, 130, 25, 28);
					panel_agregar.add(lblError_7);
					
					lblError_8 = new JLabel("X");
					lblError_8.setToolTipText("Data naixement te que ser numeric");
					lblError_8.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_8.setForeground(Color.RED);
					lblError_8.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_8.setBounds(311, 168, 25, 28);
					panel_agregar.add(lblError_8);
					
					lblError_9 = new JLabel("X");
					lblError_9.setToolTipText("Data de publicacio te que ser numeric");
					lblError_9.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_9.setForeground(Color.RED);
					lblError_9.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_9.setBounds(311, 206, 25, 28);
					panel_agregar.add(lblError_9);
					
					lblError_10 = new JLabel("X");
					lblError_10.setToolTipText("Editorial no pot estar buid");
					lblError_10.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_10.setForeground(Color.RED);
					lblError_10.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_10.setBounds(311, 244, 25, 28);
					panel_agregar.add(lblError_10);
					
					lblError_11 = new JLabel("X");
					lblError_11.setToolTipText("Nombre de pagines te que ser numeric");
					lblError_11.setHorizontalAlignment(SwingConstants.CENTER);
					lblError_11.setForeground(Color.RED);
					lblError_11.setFont(new Font("Tahoma", Font.BOLD, 16));
					lblError_11.setBounds(311, 282, 25, 28);
					panel_agregar.add(lblError_11);
					
					btnFiltre = new JButton("Filtre");
					btnFiltre.setBackground(Color.LIGHT_GRAY);
					btnFiltre.setBounds(726, 228, 129, 45);
					panel_1.add(btnFiltre);
					
					btnQuitarFiltro = new JButton("Llevar Filtre");
					btnQuitarFiltro.setBackground(Color.LIGHT_GRAY);
					btnQuitarFiltro.setBounds(726, 284, 129, 45);
					panel_1.add(btnQuitarFiltro);
					
					btnEliminarCol = new JButton("Eliminar colecció");
					btnEliminarCol.setBackground(Color.LIGHT_GRAY);
					btnEliminarCol.setBounds(33, 489, 134, 47);
					panel_1.add(btnEliminarCol);
					
					
					lblError.setVisible(false);
					lblError_1.setVisible(false);
					lblError_2.setVisible(false);
					lblError_3.setVisible(false);
					lblError_4.setVisible(false);
					lblError_5.setVisible(false);
					lblError_6.setVisible(false);
					lblError_7.setVisible(false);
					lblError_8.setVisible(false);
					lblError_9.setVisible(false);
					lblError_10.setVisible(false);
					lblError_11.setVisible(false);
		
		panel_agregar.setVisible(false);
		this.frame.setVisible(true);
	}
	
	
	public void onPressError() {
		lblError.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Titulo no pot estar buid!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Autor no pot estar buid!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Data naiximent te que ser numeric!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Data publicació te que ser numeric!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Editorial no pot estar buid!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "El nom de pagines te que ser numeric!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Titulo no pot estar buid!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Autor no pot estar buid!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Data naiximent te que ser numeric!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Data publicació te que ser numeric!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Editorial no pot estar buid!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		lblError_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "El nom de pagines te que ser numeric!","Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	
	public Vista() {
		aaa();
		onPressError();
	}
}
