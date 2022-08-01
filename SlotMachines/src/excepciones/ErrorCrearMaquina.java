package excepciones;

import java.lang.Exception;

@SuppressWarnings("serial")
public class ErrorCrearMaquina extends Exception {
	
	public ErrorCrearMaquina(String mensaje) { super(mensaje); }
}
