package models;

import java.time.LocalDate;

public class Reserva {

	private Integer id;
	private Integer idCliente;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private double valor;
	private String formapago;

	public Reserva() {
	}

	public Reserva(Integer idCliente, LocalDate fechaEntrada, LocalDate fechaSalida, double valor, String formapago) {
		this.idCliente = idCliente;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formapago = formapago;
	}

	public Reserva(Integer id, Integer idCliente, LocalDate fechaEntrada, LocalDate fechaSalida, double valor,
			String formapago) {
		this.id = id;
		this.idCliente = idCliente;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formapago = formapago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formapago;
	}

	public void setFormaPago(String formapago) {
		this.formapago = formapago;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", idCliente=" + idCliente + ", fechaEntrada=" + fechaEntrada + ", fechaSalida="
				+ fechaSalida + ", valor=" + valor + ", formapago=" + formapago + "]";
	}

}
