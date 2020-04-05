package Pruebas;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Clases.Articulo;
import Clases.OperacionEgreso;
import Clases.Organizacion;
import Clases.Servicio;
import Excepciones.esServicioException;
import Excepciones.operacionCerradaException;

public class TestCalcularValor {
	
	private Organizacion organizacionSoloServicio, organizacionSoloArticulos, organizacionMixta;
	private OperacionEgreso operacion1, operacion2, operacion3, operacion4;
	private Servicio plomeria, gasista, electricidad, limpieza, lePidioSuServicio;
	private Articulo escoba;

	@Before
	public void crearNuevaOperacion () throws operacionCerradaException
	{
		// Inicio las organizaciones y creo ya operaciones para manejar despues
		organizacionSoloServicio = new Organizacion ();
		operacion1 = new OperacionEgreso();
		operacion2 = new OperacionEgreso();
		organizacionSoloServicio.agregarNuevaOperacion(operacion1);
		organizacionSoloServicio.agregarNuevaOperacion(operacion2);
		
		organizacionSoloArticulos = new Organizacion();
		operacion3 = new OperacionEgreso();
		operacion4 = new OperacionEgreso();
		organizacionSoloArticulos.agregarNuevaOperacion(operacion3);
		
		organizacionMixta = new Organizacion();
		organizacionMixta.agregarNuevaOperacion(operacion4);
		
		// creo los items---------------------------
		
		plomeria = new Servicio (1500);
		gasista = new Servicio (2000);
		electricidad = new Servicio (3000);
		limpieza = new Servicio (150);
		lePidioSuServicio = new Servicio (10000);
	

		escoba = new Articulo (250);
		
		operacion1.agregarItem(plomeria);
		operacion1.agregarItem(plomeria);
		operacion1.agregarItem(gasista);
		
		operacion2.agregarItem(electricidad);
		try {
			operacion2.calcularValor();
		} catch (operacionCerradaException e) {}
		
		operacion3.agregarItem(escoba);
		operacion4.agregarItem(limpieza);
		operacion4.agregarItem(escoba);
		operacion4.agregarItem(lePidioSuServicio);
		
		
	// todo lo que ponga aca lo va a ejecutar antes de cada caso
	}
	
	@Test
	public void calcularPrecioBase() {
		double resultadoEsperado = 5000;
		try {
			operacion1.calcularValor();
		} catch (operacionCerradaException e) {}
		assertEquals(resultadoEsperado,operacion1.getValorOperacion(),0.1);
	}
	
	@Test
	public void cambioDePrecio()
	{
		electricidad.cambiarPrecio(43);
		try {
			operacion2.calcularValor();
		} catch (operacionCerradaException e) {}
		double resultadoEsperado = 43;
		assertEquals(resultadoEsperado,operacion2.getValorOperacion(),0.1);
	}
	
	@Test(expected = operacionCerradaException.class)
	public void cerrarOperacionYNoRecalcular() throws operacionCerradaException  
	{
			operacion1.setOperacionCerrada(false);
			operacion1.cerrarOperacion();
			plomeria.cambiarPrecio(40);
			operacion1.calcularValor();
	}
	
	@Test(expected = esServicioException.class)
	public void noGenerarRemitoParaOrganizacionMixta() throws esServicioException,operacionCerradaException
	{
		operacion4.cerrarOperacion();
		operacion4.generarDocumentoComercial();
	}
	
	@Test
	public void generarRemitoParaOrganizacionArticulos () throws operacionCerradaException, esServicioException
	{
		operacion3.cerrarOperacion();
		operacion3.generarDocumentoComercial();
		double valorOperacion = operacion3.getValorOperacion();
		
		assertEquals(valorOperacion,operacion3.getDocumentoComercial().obtenerValorDocumento(),0.1);
	}

}
