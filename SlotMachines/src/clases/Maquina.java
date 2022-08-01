package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Maquina {
	
	private int nro;
	private int recaudacion;
	private int recaudacionMin;
	private int creditoJugador;
	private int cantCasillas;
	private int costoJugada;
	private String[] frutas = {"Banana","Frutilla", "Guinda", "Manzana", "Sandía", "Uva"};
	private HashMap<Premio, Boolean> premiosDisponibles;
	
	
	public Maquina(int nro, int recaudacionIni, int cantCasillas, int costoJugada, ArrayList<Premio> premios) { //Precond: nro distinto al resto de maquinas, recaudacioIni >= 0, y cantCasillas >= 3. Esta comprobacion se hace en la interfaz.
		this.nro = nro;
		this.recaudacion = recaudacionIni;
		this.cantCasillas = cantCasillas;
		this.costoJugada = costoJugada;
		this.premiosDisponibles = new HashMap<Premio, Boolean>();
		this.recaudacionMin = 0;
		this.creditoJugador = 0;
		
		if(premios!=null) {
			for(Premio premio : premios) {
				if(premio.cantCasillas()==cantCasillas) {
					this.premiosDisponibles.put(premio, true);
				}
			}
		}
		actualizarPremios();
	}
	
	public ArrayList<String> crearCombinacion() {
		ArrayList<String> jugada = new ArrayList<String>();
	
		for(int i=0; i<cantCasillas; i++) {
			jugada.add(frutas[new Random().nextInt(frutas.length)]);
		}
		
		return jugada;
	}
	
	public int premio(ArrayList<String> jugada) {
		creditoJugador -= costoJugada;
		recaudacion += costoJugada;
		actualizarPremios();
		for(Premio premio : premiosDisponibles.keySet()) {
			if(premio.esCombinacion(jugada))
				if(premiosDisponibles.get(premio)) { 
					return premio.valor(); // Es premio, devuelve el valor del mismo
				}else {
					return -1; // Es premio pero no esta habilitado (no se puede pagar)
				}
		}
		return 0; // No es premio
	}
	
	public void aceptarPremio(int premio) {
		if(premio>0) {
			this.recaudacion -= premio;
			this.creditoJugador += premio;
			actualizarPremios();
		}
		
	}
	
	private void actualizarPremios() {
		for(Premio premio : premiosDisponibles.keySet()) {
			if(recaudacion<premio.valor()) {
				premiosDisponibles.replace(premio, false);
			}
			else {
				premiosDisponibles.replace(premio, true);
			}
			if(recaudacionMin<premio.valor()) {
				recaudacionMin = premio.valor(); // Setea la recaudacion minima al premio con mayor valor. Es decir que cuando la recaudacion normal sea menor, habra premios que no se podran pagar.
			}
		}
	}
	
	public boolean creditoSuficiente() {
		return creditoJugador >= costoJugada;
	}
	
	public void actualizarCredito(int valor) {
		this.creditoJugador += valor;
	}
	
	public int esLaNro() {
		return this.nro;
	}
	
	public int creditoJugador() {
		return this.creditoJugador;
	}
	
	public int costoJugada() {
		return this.costoJugada;
	}

	public void retirarCredito() {
		this.creditoJugador = 0;
	}

	public int recaudacion() {
		return this.recaudacion;
	}
	
	public boolean premiosNoDisponibles() {
		return recaudacionMin>recaudacion;
	}
		
	public void agregarPremio(Premio premio) {  
		if(premio.cantCasillas()==cantCasillas) {
			this.premiosDisponibles.put(premio, true);	
		}
		actualizarPremios();
	}	
}
   
 










