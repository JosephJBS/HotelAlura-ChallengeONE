package models;

public enum FormaPago {

	CREDITO("Tarjeta de Crédito"),
	DEBITO("Tarjeta de Débito "),
	EFECTIVO("Dinero en efectivo");
	
	private final String descripcion;

    private FormaPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
}
