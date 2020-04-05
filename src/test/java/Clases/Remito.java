package Clases;

public class Remito implements DocumentoComercial {
	
	double valor;
	public Remito (double valorOperacion)
	{
		this.valor=valorOperacion;
	}
	
	public double obtenerValorDocumento()
	{
		return this.valor;
	}
}