package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clases.Controlador;

public class VentanaJugar extends JFrame {
		
	private static final long serialVersionUID = -2942967262427396255L;
	private int nroMaquina;		
	private JButton btnJugar, btnAgregarCredito, btnRetirarDinero;
	private JLabel lblCredito, lblRecaudacion;
	private JTextField txtflCredito;
	private boolean avisadoPremiosNoDisponibles = false;
	private boolean avisadoPremiosDisponibles = true;

	
	public VentanaJugar(int IDMaquina) {	
		this.nroMaquina = IDMaquina;
		
		configurar();				
		asignacionEventos();
		setTitle(" Tragamonedas " + nroMaquina);	
		setMinimumSize(new Dimension(350,500));
		setMaximumSize(new Dimension(350,500));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}	
	
	
	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);	
		
		lblCredito = new JLabel();
		lblCredito.setText("Credito: $"  +  Controlador.getInstance().mostrarCredito(nroMaquina));		
		lblCredito.setBounds(10,250,200,50);		
		lblCredito.setBackground(Color.black);
		lblCredito.setForeground(Color.black);				
		
		lblRecaudacion = new JLabel();
		lblRecaudacion.setText("Recaudacion: $"+ Controlador.getInstance().mostrarRecaudacion(nroMaquina));
		lblRecaudacion.setBounds(200,250,150,50);
		lblRecaudacion.setBackground(Color.black);
		lblRecaudacion.setForeground(Color.black);
		
		btnJugar = new JButton("Jugar  $" + Controlador.getInstance().mostrarPrecioJugada(nroMaquina));
		btnJugar.setBounds(10,30, 314 ,100);
		btnJugar.setBackground(Color.LIGHT_GRAY);
		btnJugar.setForeground(Color.black);	
		
		btnAgregarCredito = new JButton("Agregar Credito");	
		btnAgregarCredito.setBounds(10,150, 150 ,60);
		btnAgregarCredito.setBackground(Color.LIGHT_GRAY);
		btnAgregarCredito.setForeground(Color.black);
		btnAgregarCredito.setOpaque(true);		
		
		btnRetirarDinero = new JButton("Retirar Dinero");
		btnRetirarDinero.setBounds(170,150, 150 ,100);
		btnRetirarDinero.setBackground(Color.LIGHT_GRAY);
		btnRetirarDinero.setForeground(Color.black);
		
		txtflCredito = new JTextField ("");	
		txtflCredito.setBounds(10,220,150 ,30);
		txtflCredito.setBackground(Color.LIGHT_GRAY);
		txtflCredito.setForeground(Color.black);	
		
		
		c.add(lblCredito);
		c.add(btnJugar);
		c.add(btnAgregarCredito);
		c.add(btnRetirarDinero);
		c.add(txtflCredito);
		c.add(lblRecaudacion);	
		
		
	}
	
	
	private void asignacionEventos() {	
		ActionListener listener = new ManejoEvento();	
		btnJugar.addActionListener(listener);	
		btnAgregarCredito.addActionListener(listener);
		btnRetirarDinero.addActionListener(listener);
	}	

	private void jugar() {
		ArrayList<String> jugada = Controlador.getInstance().obtenerCombinacion(nroMaquina);
		int premio = Controlador.getInstance().obtenerPremio(nroMaquina, jugada);			
		if(Controlador.getInstance().puedeJugar(nroMaquina)) {
			if(premio>0) {		
				JOptionPane.showMessageDialog(null, jugada + "\n Es Premio! $" + premio); // Habria q cambiarlo por una ventna que pida confirmar
				int input = JOptionPane.showConfirmDialog(null,"Aceptas El Premio?","Aceptas El Premio?", JOptionPane.YES_NO_OPTION);
				if (input == JOptionPane.YES_OPTION) {
					Controlador.getInstance().aceptarPremio(nroMaquina, premio);		
				}																											
			}
			else if(premio==0) {
				JOptionPane.showMessageDialog(null, jugada + "\n  No es premio :(");
			}
			else if(premio==-1) {
				JOptionPane.showMessageDialog(null, jugada + "\n Es premio pero no se puede pagar. \n Usted fue advertido.");
			}
			
			lblCredito.setText("Credito: $"  +  Controlador.getInstance().mostrarCredito(nroMaquina));
			lblRecaudacion.setText("Recaudacion: $" + Controlador.getInstance().mostrarRecaudacion(nroMaquina));
			
			if(!Controlador.getInstance().recaudacionMinAlcanzada(nroMaquina) && !avisadoPremiosDisponibles) {
				avisadoPremiosNoDisponibles = false;
				if(!avisadoPremiosDisponibles) {
					JOptionPane.showMessageDialog(null, "Todos los premios estan disponibles nuevamente.");
					avisadoPremiosDisponibles = true;
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Credito insuficiente.");
		}		
	}
	
	
    class ManejoEvento implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent boton) {	
			if (boton.getSource().equals(btnJugar)) {
				if(Controlador.getInstance().recaudacionMinAlcanzada(nroMaquina)) {		
					if(!avisadoPremiosNoDisponibles) { // Para que no siga avisando
						avisadoPremiosNoDisponibles = true;
						avisadoPremiosDisponibles = false;
						JOptionPane.showMessageDialog(null, " Hay premios que no se podran pagar.\n No se le volvera a avisar hasta que\n esten todos disponibles nuevamente.");
					}else {
						jugar();
					}
				} else {
					jugar();
				}
			}
			
			else if (boton.getSource().equals(btnAgregarCredito)) {					
				try {
					if (Integer.parseInt(txtflCredito.getText())<0){
						txtflCredito.setText("0");
					}
					Controlador.getInstance().agregarCreditoJugador(nroMaquina, Integer.parseInt(txtflCredito.getText()));
					lblCredito.setText("Credito: $"  +  Controlador.getInstance().mostrarCredito(nroMaquina));
					txtflCredito.setText("");							 
			    }
			    catch (NumberFormatException ex) {
			      	JOptionPane.showMessageDialog(null, "El credito a agregar tiene que ser un numero.");
			      	txtflCredito.setText("");
			    }						
			}
			
			else if(boton.getSource().equals(btnRetirarDinero)) {
				if(Controlador.getInstance().mostrarCredito(nroMaquina)>0) {
					JOptionPane.showMessageDialog(null, "Credito retirado: $" + Controlador.getInstance().mostrarCredito(nroMaquina));
					Controlador.getInstance().retirarDinero(nroMaquina);			
					lblCredito.setText("Credito: $"  +  0);
					
				}else {
					JOptionPane.showMessageDialog(null, "No tiene credito para retirar.");
				}
			}
		}
    }
}


