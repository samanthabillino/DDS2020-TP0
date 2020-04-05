package Clases;

import java.util.ArrayList;

import Excepciones.esServicioException;
import Excepciones.operacionCerradaException;


public class OperacionEgreso {

	//hola
	public ArrayList <Item> listaItems = new ArrayList<Item>();
	boolean operacionCerrada; 
	double valorOperacion;
    DocumentoComercial documentoComercialCorresp;
    
    public boolean getOperacionCerrada()
    {
    	return this.operacionCerrada;
    }
    
    public double getValorOperacion()
    {
    	return this.valorOperacion;
    }
    
   public void setOperacionCerrada(boolean estado )
   {
	   this.operacionCerrada = estado ;
   }
    
   public void setValorOperacion(double valor)
   {
	   this.valorOperacion = valor;
   }
   
   private void calcularValor ()
   {
	   
		   double valor = listaItems.stream().mapToDouble(item->item.getValor()).sum();
		   this.setValorOperacion(valor);
   }
   
   public void cerrarOperacion () throws operacionCerradaException
   {
	   if (this.getOperacionCerrada())
	   {
		   throw new operacionCerradaException("La operacion ya se encuentra cerrada, no se puede calcular el valor");
	   }
	   else
	   {
		   this.calcularValor();
	   }
   }

public void generarDocumentoComercial () throws esServicioException 
{
	if (this.getOperacionCerrada())
	{
		if (this.listaItems.stream().anyMatch(item->item.esServicio()))
		{
			throw new esServicioException ("Uno o más items de la lista son servicios, por lo cual no se ha podido generar un documento comercial");
		}
		else
		{
			documentoComercialCorresp = new Remito (this.getValorOperacion());
		}
	}
}
}

