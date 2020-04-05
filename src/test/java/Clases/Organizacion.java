package Clases;
import java.util.ArrayList;

public class Organizacion {

	public ArrayList <OperacionEgreso> listaOperacionesEgreso = new ArrayList<OperacionEgreso>();
	
	public void agregarNuevaOperacion(OperacionEgreso operacion)
	{
		listaOperacionesEgreso.add(operacion);
	}
}
