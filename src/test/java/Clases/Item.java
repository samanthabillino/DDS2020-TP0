package Clases;

public  class Item {
	
	public double precio;
	
	public Item(double precio) {
		this.precio = precio;
	}
	
	public void cambiarPrecio(double precioNuevo) {
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
