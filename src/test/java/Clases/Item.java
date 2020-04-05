package Clases;

public  class Item {
	
	public double precio;
	
	protected void cambiarPrecio(double precioNuevo) {
		this.precio=precioNuevo;

}
	public double obtenerPrecio() {
		return this.precio;
	}
	
	public  boolean esServicio() {
		return false;
	}
	public double getValor() {
		return this.precio;
	}
}
