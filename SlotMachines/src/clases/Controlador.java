package clases;

import excepciones.ErrorCrearMaquina;
import excepciones.ErrorCrearPremio;

import java.util.ArrayList;
import java.util.Collections;

public class Controlador {
	
	private static ArrayList<Maquina> maquinas = new ArrayList<Maquina>();
	private static ArrayList<Premio> premios = new ArrayList<Premio>();
	private static Controlador instancia;
	
	
	public static Controlador getInstance() {
		if (instancia == null)
			instancia = new Controlador();
			
		return instancia;
	}
	
	
	public void crearMaquina(int nro, int recaudacionIni, int cantCasillas, int costoJugada) throws ErrorCrearMaquina {
		if(nro<0) {
			throw new ErrorCrearMaquina("Numero de maquina no puede ser negativo.");
		}
		if(recaudacionIni<0) {
			throw new ErrorCrearMaquina("La recaudacion no puede ser negativa.");
		}
		if(costoJugada<0) {
			throw new ErrorCrearMaquina("El precio de jugada no puede ser negativo.");
		}
		else if(buscarMaquina(nro)==null) {
			Maquina m = new Maquina(nro, recaudacionIni, cantCasillas, costoJugada, premios);
			maquinas.add(m);
		}
		else {
			throw new ErrorCrearMaquina("La maquina "+ nro +" ya existe.");
		}
	}
	
	public void borrarMaquina (int nro) {
		Maquina maquina = buscarMaquina(nro);
		if(maquina != null) {
			maquinas.remove(maquina);
		}
	}
	
	public void borrarPremio (ArrayList<String> combinacion) {
		Premio premio = buscarPremio(combinacion);
		if(premio != null) {
			premios.remove(premio);
		}
	}	
	
	public void crearPremio(ArrayList<String> combinacion, int valor) throws ErrorCrearPremio {
		Collections.sort(combinacion);
		
		if(valor<=0) {
			throw new ErrorCrearPremio("El valor del premio debe ser mayor a 0.");
		}
		else if(buscarPremio(combinacion)==null) {
			premios.add(new Premio(combinacion, valor));
		}else {
			throw new ErrorCrearPremio("El premio ya existe.");
		}
	}
	
	public void aceptarPremio(int nro,int premio) {
		buscarMaquina(nro).aceptarPremio(premio);
	}
	
	public  ArrayList<Integer> obtenerNrosMaquinas() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		for(Maquina maquina : maquinas) {
			lista.add(maquina.esLaNro());
		}
		return lista;
 	}
	
	private Maquina buscarMaquina(int nro) {
		for(Maquina maquina : maquinas) {
			if(maquina.esLaNro()==nro) {
				return maquina;
			}
		}
		return null;
	}
	
	private Premio buscarPremio(ArrayList<String> combinacion) {
		for(Premio premio : premios) {
			if(premio.esCombinacion(combinacion)) {
				return premio;
			}
		}
		return null;
	}
	
	public ArrayList<String> obtenerCombinacion(int nroMaquina){
		return buscarMaquina(nroMaquina).crearCombinacion();
	}
	
	public int obtenerPremio(int nroMaquina, ArrayList<String> jugada){
		return buscarMaquina(nroMaquina).premio(jugada);
	}

	public void retirarDinero(int nroMaquina) {
		buscarMaquina(nroMaquina).retirarCredito();
	}
	
	public int mostrarPrecioJugada(int nroMaquina) {
		return buscarMaquina(nroMaquina).costoJugada();
	}
	
	public int mostrarCredito(int nroMaquina) {
		return buscarMaquina(nroMaquina).creditoJugador();
	}
	
	public int mostrarRecaudacion(int nroMaquina) {
		return buscarMaquina(nroMaquina).recaudacion();
	}

	public void agregarCreditoJugador(int nroMaquina, int credito) {
		buscarMaquina(nroMaquina).actualizarCredito(credito);
	}

	public boolean recaudacionMinAlcanzada(int nroMaquina) {
		return buscarMaquina(nroMaquina).premiosNoDisponibles();
	}

	public boolean puedeJugar(int nroMaquina) {
		return buscarMaquina(nroMaquina).creditoSuficiente();
	}
	
	public ArrayList<ArrayList<String>> obtenerListaPremios(){
		ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();		
		for(Premio premio :premios) {
			lista.add(premio.Combo());
		}	
		return lista;
	}
	
	public void actualizarPremios() {           //para actualizar los premios de todas las maquinas
		for(Maquina maquina : maquinas) {
			for(Premio premio : premios) {
				maquina.agregarPremio(premio);
			}
		}
	}
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

