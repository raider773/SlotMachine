package GUI;

import clases.Controlador;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.VentanaUno;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class VentanaUno extends JFrame {
			
	private static final long serialVersionUID = 1L;
	private JButton btnUsarMaquina,btnAdmin, btnActualizar;
	private JComboBox<Integer> nrosMaquinas;


	public VentanaUno() {	
 		configurar();				
		asignacionEventos();
		
		setMinimumSize(new Dimension(400,200));
		setMaximumSize(new Dimension(400,200));; 
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);
		
		ArrayList<Integer> listaNrosMaquinas = Controlador.getInstance().obtenerNrosMaquinas();
		
	    btnUsarMaquina = new JButton("Usar Maquina:");
	    btnUsarMaquina.setBounds(10, 10, 250, 50);
	    
	    btnAdmin = new JButton("Administrar"); 
	    btnAdmin.setBounds(10, 70, 170, 80);
	    
	    nrosMaquinas = new JComboBox<Integer>();
	    nrosMaquinas.setBounds(270, 10, 100, 50);
	    for(Integer nro : listaNrosMaquinas) {
	    	nrosMaquinas.addItem(nro);
	    } 
	    if(listaNrosMaquinas.size()==0) {
	    	nrosMaquinas.setSelectedIndex(-1);
	    	btnUsarMaquina.setEnabled(false);
	    }
	    
	    btnActualizar = new JButton("Actualizar");
	    btnActualizar.setBounds(200, 70, 170, 80);	  
	            
	    c.add(btnUsarMaquina);
	    c.add(btnAdmin);
	    c.add(nrosMaquinas);
	    c.add(btnActualizar);   
	}
	
	
	private void asignacionEventos() {	
		ActionListener listener = new ManejoEvento();	
		btnUsarMaquina.addActionListener(listener);
		btnAdmin.addActionListener(listener);	
		btnActualizar.addActionListener(listener);		
	}
	
	private void actualizar() {
		Integer n = (Integer) nrosMaquinas.getSelectedItem();
		nrosMaquinas.removeAllItems();
		int i = 0;
		for(Integer nro :Controlador.getInstance().obtenerNrosMaquinas()) {
	    	nrosMaquinas.addItem(nro);		
	    	if(nro==n) {
	    		nrosMaquinas.setSelectedIndex(i);
	    	}
	    	i++;
		}
		if(Controlador.getInstance().obtenerNrosMaquinas().size()>0) {
			btnUsarMaquina.setEnabled(true);				
	    }else if(Controlador.getInstance().obtenerNrosMaquinas().size()==0) {
	    	btnUsarMaquina.setEnabled(false);	
	    }
	}
	
	
    class ManejoEvento implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent boton) {	
			if (boton.getSource().equals(btnUsarMaquina)) {	
				int n = (int) nrosMaquinas.getSelectedItem();
				actualizar();
				if(nrosMaquinas.getSelectedItem()!=null && (int)nrosMaquinas.getSelectedItem()==n) {	
					VentanaJugar VentanaJugar = new VentanaJugar(n);	
					VentanaJugar.setVisible(true);	
				}else {
					JOptionPane.showMessageDialog(null, "La maquina en la que intenta jugar fue eliminada.");
				}
			}
			
			else if (boton.getSource().equals(btnAdmin)) {	
			    VentanaAdmin VentanaAdmin = new VentanaAdmin();	
			    VentanaAdmin.setVisible(true);			 			       				      
			}
			
			else if (boton.getSource().equals(btnActualizar)) {
				actualizar();
			}
		}
    }
}


							
				

							
			
		
    	
    	
	
		
    	
	
	
