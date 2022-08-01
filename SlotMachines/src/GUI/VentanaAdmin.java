package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;



import clases.Controlador;
import excepciones.ErrorCrearMaquina;
import excepciones.ErrorCrearPremio;



public class VentanaAdmin extends JFrame {
	private static final long serialVersionUID = 8161237802956326374L;
	
	private JPanel panelCrearMaquina,panelCrearPremio,panelBorrar;
	JLabel tituloPnl1, lblTituloPnl2, tituloPnl3;
	JLabel etNroMaquina, etSpnCantCasillas, etPrecio, etRecaudacion;
	JLabel etFruta1, etFruta2, etFruta3, etValorPremio;
	JTextField txtflNroMaquina, txtflRecaudacion, txtflPrecio;
	JTextField txtflValorPremio;
	JSpinner spnCantCasilla;
	JButton btnCrearMaquina;
	JButton btnAgregarFruta, btnCrearPremio, btnSacarFruta,btnBorrarMaquina,btnBorrarPremio;
	JComboBox<String> cboxFrutas1, cboxFrutas2, cboxFrutas3;
	JComboBox<Integer> nrosMaquinas;
	JComboBox<ArrayList<String>> ListaPremios;
	
	
	private String[] frutas = {"Banana","Frutilla", "Guinda", "Manzana", "Sandía", "Uva"};
	private ArrayList<JComboBox<String>> cboxesFrutas;
	
	public VentanaAdmin() {
		JTabbedPane opciones;
		opciones = new JTabbedPane();
		
		cfgPanelBorrar(Controlador.getInstance().obtenerNrosMaquinas(),Controlador.getInstance().obtenerListaPremios());
		cfgPanelCrearMaqunia();	
		cfgPanelCrearPremio();
		asignacionEventos();

		opciones.addTab("Crear Maquina", panelCrearMaquina);
		opciones.addTab("Crear Premio", panelCrearPremio);
		opciones.addTab("Borrar", panelBorrar);

		getContentPane().add(opciones);

		setTitle("Administrador"); 				
		setMinimumSize(new Dimension(400,310));
		setMaximumSize(new Dimension(400,310));
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	
	private void cfgPanelBorrar(ArrayList<Integer> listaNrosMaquinas, ArrayList<ArrayList<String>> Premios) {
		panelBorrar = new JPanel();
		panelBorrar.setLayout(new GridLayout(2,2));
		
		btnBorrarMaquina = new JButton ("Borrar Maquina");
		if(Controlador.getInstance().obtenerNrosMaquinas().size()==0) {
	    	btnBorrarMaquina.setEnabled(false);	
	    }				
		
		btnBorrarPremio = new JButton ("Borrar Premio");
		
		nrosMaquinas = new JComboBox<Integer>();
	    for(Integer nro : listaNrosMaquinas) {
	    	nrosMaquinas.addItem(nro);
	    } 
		
	    ListaPremios = new JComboBox<ArrayList<String>>();;
	    for(ArrayList<String> premio : Premios) {
	    	ListaPremios.addItem(premio);
	    }     
	   
	    panelBorrar.add(nrosMaquinas);	 
	    panelBorrar.add(btnBorrarMaquina);
	    panelBorrar.add(ListaPremios);
	    panelBorrar.add(btnBorrarPremio);	    
	    
	    
	}
	
	private void cfgPanelCrearMaqunia() {
		panelCrearMaquina = new JPanel();
		panelCrearMaquina.setLayout(null);
		
		cboxesFrutas = new ArrayList<JComboBox<String>>();
		
		tituloPnl1= new JLabel();
		tituloPnl1.setText("Configuracion de maquina a crear");
		tituloPnl1.setBounds(5, 5, 1000, 15);
		
		etNroMaquina = new JLabel("Numero ID:");
		etNroMaquina.setBounds(5, 30, 100, 20);
		txtflNroMaquina = new JTextField();
		txtflNroMaquina.setBounds(130, 30, 70, 20);
		
		etSpnCantCasillas = new JLabel("Cantidad de casillas:");
		etSpnCantCasillas.setBounds(5, 60, 160, 20);
		SpinnerNumberModel model = new SpinnerNumberModel(3, 3, 6, 1); //Para establecer limites.
		spnCantCasilla = new JSpinner(model);
		spnCantCasilla.setBounds(160, 60, 40, 20);
		
		etPrecio = new JLabel("Precio de jugada:");
		etPrecio.setBounds(5, 90, 130, 20);
		txtflPrecio = new JTextField();
		txtflPrecio.setBounds(130, 90, 70, 20);
		
		etRecaudacion = new JLabel("Recaudacion:");
		etRecaudacion.setBounds(5, 120, 130, 20);
		txtflRecaudacion = new JTextField();
		txtflRecaudacion.setBounds(130, 120, 70, 20);
		
		btnCrearMaquina = new JButton(" CREAR MAQUINA ");
		btnCrearMaquina.setBounds(5, 150, 150, 30);
		
		panelCrearMaquina.add(tituloPnl1);
		panelCrearMaquina.add(etNroMaquina);
		panelCrearMaquina.add(txtflNroMaquina);
		panelCrearMaquina.add(etSpnCantCasillas);
		panelCrearMaquina.add(spnCantCasilla);
		panelCrearMaquina.add(etPrecio);
		panelCrearMaquina.add(txtflPrecio);
		panelCrearMaquina.add(etRecaudacion);
		panelCrearMaquina.add(txtflRecaudacion);
		panelCrearMaquina.add(btnCrearMaquina);
		add(panelCrearMaquina);	
	}
	
	private void cfgPanelCrearPremio() {
		panelCrearPremio = new JPanel();
		panelCrearPremio.setLayout(null);
	
		lblTituloPnl2= new JLabel("Combinacion del premio:");
		lblTituloPnl2.setBounds(5, 5, 300, 15);
			
		cboxFrutas1 = new JComboBox<String>();
		for(String fruta : frutas) {
			cboxFrutas1.addItem(fruta);
		}
		cboxFrutas1.setSelectedIndex(0);
		cboxFrutas1.setBounds(5, 30, 80, 20);
		cboxesFrutas.add(cboxFrutas1);
		
		cboxFrutas2 = new JComboBox<String>();
		for(String fruta : frutas) {
			cboxFrutas2.addItem(fruta);
		}
		cboxFrutas2.setSelectedIndex(1);
		cboxFrutas2.setBounds(5, 60, 80, 20);
		cboxesFrutas.add(cboxFrutas2);
		
		cboxFrutas3 = new JComboBox<String>();
		for(String fruta : frutas) {
			cboxFrutas3.addItem(fruta);
		}
		cboxFrutas3.setSelectedIndex(2);
		cboxFrutas3.setBounds(5, 90, 80, 20);
		cboxesFrutas.add(cboxFrutas3);	
		
		btnAgregarFruta = new JButton("+");
		btnAgregarFruta.setBounds(5, 120, 46, 20);
		
		btnSacarFruta = new JButton("-");
		btnSacarFruta.setBounds(55, 120, 46, 20);
		btnSacarFruta.setEnabled(false);
		
		etValorPremio = new JLabel("Valor:");
		etValorPremio.setBounds(200, 30, 60, 20);
		txtflValorPremio = new JTextField();
		txtflValorPremio.setBounds(260, 30, 90, 20);
		
		btnCrearPremio = new JButton(" CREAR PREMIO ");
		btnCrearPremio.setBounds(200, 60, 150, 20);
		
		panelCrearPremio.add(lblTituloPnl2);
		panelCrearPremio.add(cboxFrutas1);
		panelCrearPremio.add(cboxFrutas2);
		panelCrearPremio.add(cboxFrutas3);
		panelCrearPremio.add(btnAgregarFruta);
		panelCrearPremio.add(btnSacarFruta);
		panelCrearPremio.add(etValorPremio);
		panelCrearPremio.add(txtflValorPremio);
		panelCrearPremio.add(btnCrearPremio);
		
		add(panelCrearPremio);
	}
	
		
	private void asignacionEventos() {	
		ActionListener listener = new ManejoEvento();	
		btnCrearMaquina.addActionListener(listener);
		btnAgregarFruta.addActionListener(listener);
		btnSacarFruta.addActionListener(listener);
		btnCrearPremio.addActionListener(listener);		
		btnBorrarMaquina.addActionListener(listener);
		btnBorrarPremio.addActionListener(listener);
	}	
	
	
	class ManejoEvento implements ActionListener {
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent boton) {
			
			if(boton.getSource().equals(btnCrearMaquina)) { // Crear maquina
				try {
					int nro = Integer.parseInt(txtflNroMaquina.getText());
					int recaudacion = Integer.parseInt(txtflRecaudacion.getText());
					int casillas = (int) spnCantCasilla.getValue();
					int costoJugada = Integer.parseInt(txtflPrecio.getText());
					Controlador.getInstance().crearMaquina(nro, recaudacion, casillas, costoJugada);
					
					JOptionPane.showMessageDialog(null, "Maquina creada con exito.");
					Controlador.getInstance().actualizarPremios();
					if(Controlador.getInstance().obtenerNrosMaquinas().size()>0) {
						btnBorrarMaquina.setEnabled(true);				
				    }else if(Controlador.getInstance().obtenerNrosMaquinas().size()==0) {
				    	btnBorrarMaquina.setEnabled(false);	
				    }
					
					
				}
				catch (ErrorCrearMaquina ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Numero ID, Precio jugada y Recaudacion deben ser numeros enteros.");
				}
				
				nrosMaquinas.removeAllItems();
				for(Integer nro :Controlador.getInstance().obtenerNrosMaquinas()) {
			    	nrosMaquinas.addItem(nro);					    	
				}
				
			}
			
			else if(boton.getSource().equals(btnAgregarFruta)) { // Agregar una fruta mas a la combinacion
				JComboBox<String> nueva = new JComboBox<String>();
				
				for(String fruta : frutas) {
					nueva.addItem(fruta);
				}
				nueva.setBounds(5,(cboxesFrutas.get(cboxesFrutas.size()-1).getBounds().y)+30,80,20);
				cboxesFrutas.add(nueva);
				
				btnAgregarFruta.setBounds(5, btnAgregarFruta.getBounds().y + 30, 46, 20);
				btnSacarFruta.setBounds(55, btnSacarFruta.getBounds().y + 30, 46, 20);
				btnSacarFruta.setEnabled(true);
				
				panelCrearPremio.add(nueva);
				
				if(cboxesFrutas.size()>=6) {
					btnAgregarFruta.setEnabled(false);
				}
				
				panelCrearPremio.validate();
				panelCrearPremio.repaint(); // Redibuja para evitar un problema visual (no aparece la flecha de la combobox hasta hacer click en algun lado)
				
			}
			
			else if(boton.getSource().equals(btnSacarFruta)) { // Sacar la ultima fruta agregada
				panelCrearPremio.remove(cboxesFrutas.get(cboxesFrutas.size()-1));
				cboxesFrutas.remove(cboxesFrutas.size()-1);
				btnAgregarFruta.setBounds(5, btnAgregarFruta.getBounds().y - 30, 46, 20);
				btnSacarFruta.setBounds(55, btnSacarFruta.getBounds().y - 30, 46, 20);
				if(cboxesFrutas.size()<=3) {
					btnSacarFruta.setEnabled(false);
				}
				btnAgregarFruta.setEnabled(true);	
				
				panelCrearPremio.validate();
				panelCrearPremio.repaint(); // Redibuja el panel para borrar el combobox de frutas sacado				
			}
			
			else if(boton.getSource().equals(btnCrearPremio)) { // Crear el premio con los datos elegidos
				ArrayList<String> combinacion = new ArrayList<String>();
				for(JComboBox<String> frutaElegida : cboxesFrutas) {
					combinacion.add((String) frutaElegida.getSelectedItem());
				}
				
				try {
					Controlador.getInstance().crearPremio(combinacion, Integer.parseInt(txtflValorPremio.getText()));
					
					JOptionPane.showMessageDialog(null, "Premio creado con exito."); 
					
					Controlador.getInstance().actualizarPremios();	
				}
				catch (ErrorCrearPremio ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				catch (NumberFormatException ex1) {
			      	JOptionPane.showMessageDialog(null, "El valor del premio debe ser un numero.");
			      	txtflValorPremio.setText("");
			    }	
				
				ListaPremios.removeAllItems();		
			    for(ArrayList<String> Comb :Controlador.getInstance().obtenerListaPremios()) {
			    	ListaPremios.addItem(Comb);				
			    }							
			} 
			
			else if (boton.getSource().equals(btnBorrarMaquina)) {
					Controlador.getInstance().borrarMaquina((int) nrosMaquinas.getSelectedItem());		
					
					nrosMaquinas.removeAllItems();
					for(Integer nro :Controlador.getInstance().obtenerNrosMaquinas()) {
				    	nrosMaquinas.addItem(nro);					    	
					}
					
					if(Controlador.getInstance().obtenerNrosMaquinas().size()>0) {
						btnBorrarMaquina.setEnabled(true);				
				    }else if(Controlador.getInstance().obtenerNrosMaquinas().size()==0) {
				    	btnBorrarMaquina.setEnabled(false);	
				    }
			}
			
			else if (boton.getSource().equals(btnBorrarPremio)) {
				Controlador.getInstance().borrarPremio((ArrayList<String>) ListaPremios.getSelectedItem());	
				
				ListaPremios.removeAllItems();		
			    for(ArrayList<String> Comb : Controlador.getInstance().obtenerListaPremios()) {
			    	ListaPremios.addItem(Comb);
			    }
			}
		}
	}
}











