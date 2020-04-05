package Clases;

import java.util.ArrayList;

import Excepciones.esServicioException;
import Excepciones.operacionCerradaException;


public class OperacionEgreso {

	public ArrayList <Item> listaItems = new ArrayList<Item>();
	boolean operacionCerrada=false; 
	double valorOperacion;
    public DocumentoComercial documentoComercialCorresp;
    
    public DocumentoComercial getDocumentoComercial()
    {
    	return this.documentoComercialCorresp;
    }
    
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
   public void agregarItem(Item nuevoItem) throws operacionCerradaException
   
   {
	   if(!this.getOperacionCerrada())
	   {
	   this.listaItems.add(nuevoItem);
	   }
	   
	   else 
	   {
		   throw new operacionCerradaException("La operacion se encuentra cerrada, no se pueden ingresar más items");
	   }
   }
   public void calcularValor () throws operacionCerradaException
   {
	   if (!this.getOperacionCerrada())
	   {
		   double valor = listaItems.stream().mapToDouble(item->item.getValor()).sum();
		   this.setValorOperacion(valor);
	   }
	   
	   else
	   {
		   throw new operacionCerradaException("La operacion ya se encuentra cerrada, no es posible recalcular el valor total");
	   }
   }
   
   public void cerrarOperacion () throws operacionCerradaException
   {
	   if (this.getOperacionCerrada())
	   {
		   throw new operacionCerradaException("La operacion ya se encuentra cerrada");
	   }
	   else
	   {
		   this.calcularValor();
		   this.setOperacionCerrada(true);
	   }
   }

public void generarDocumentoComercial () throws esServicioException, operacionCerradaException 
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
	else
	{
		throw new operacionCerradaException("La operacion aun no se encuentra cerrada, por lo cual no es posible generar un documento comercial");
	}
}
}

