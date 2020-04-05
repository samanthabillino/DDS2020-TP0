package Clases;

public class Servicio extends Item {

	public Servicio (double precio)
	{
		super(precio);
	}
	@Override
	public boolean esServicio() {
		return true;
	}
	
	


}
