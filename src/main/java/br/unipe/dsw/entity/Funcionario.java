package br.unipe.dsw.entity;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Funcionario extends Pessoa implements Serializable {

	private String matricula;
	private String ctps;

	public Funcionario(long id) {
		super(id);
	}

	public Funcionario() {
		super();

	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

}
