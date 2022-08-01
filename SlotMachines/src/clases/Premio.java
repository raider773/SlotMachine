package clases;

import java.util.ArrayList;
import java.util.Collections;

public class Premio {
	
	private ArrayList<String> combo;
	private int valor;
	private int cantCasillas;
	
	public Premio(ArrayList<String> combinacion, int valor) {
		Collections.sort(combinacion); //Para estar seguros.
		combo = new ArrayList<String>();
		this.combo = combinacion;
		this.valor = valor;
		this.cantCasillas = combinacion.size();
	}
	
	public boolean esCombinacion(ArrayList<String> jugada) {
		Collections.sort(jugada);
		Collections.sort(combo);	
		return combo.equals(jugada);
	}
	
	public int cantCasillas() {
		return cantCasillas;
	}
	
	public int valor() {
		return valor;
	}
	
	public void cambiarValor(int nuevoValor) {
		valor = nuevoValor;
	}
	
	public ArrayList<String> Combo() {
		return combo;
	}
}
