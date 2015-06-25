package br.unipe.dsw.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Hospede extends Pessoa implements Serializable{

	@OneToMany(mappedBy="hospede")
	private List<Reserva> reservas;

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

}
