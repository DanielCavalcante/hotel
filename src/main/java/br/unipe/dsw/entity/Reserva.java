package br.unipe.dsw.entity;

import br.unipe.dsw.enums.EnumFormaDePagamento;
import br.unipe.dsw.enums.EnumStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataInicial;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataFinal;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCheckin;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataChekout;

	private boolean isPago;

	@Enumerated(EnumType.STRING)
	private EnumFormaDePagamento formaPagamento;

	@Enumerated(EnumType.STRING)
	private EnumStatus status;

	private float valorPago;

	@ManyToOne
	private Hospede hospede;

	@ManyToOne
	private Funcionario funcionario;

	@ManyToOne
	private Quarto quarto;

	@OneToMany(mappedBy = "reserva")
	private List<Consumo> consumo;

	public Reserva(long id) {
		super();
		this.id = id;
	}

	public Reserva() {
		super();

	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public List<Consumo> getConsumo() {
		return consumo;
	}

	public void setConsumo(List<Consumo> consumo) {
		this.consumo = consumo;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getDataCheckin() {
		return dataCheckin;
	}

	public void setDataCheckin(Date dataCheckin) {
		this.dataCheckin = dataCheckin;
	}

	public Date getDataChekout() {
		return dataChekout;
	}

	public void setDataChekout(Date dataChekout) {
		this.dataChekout = dataChekout;
	}

	public EnumFormaDePagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(EnumFormaDePagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public boolean isPago() {
		return isPago;
	}

	public void setPago(boolean isPago) {
		this.isPago = isPago;
	}

	public float getValorPago() {
		return valorPago;
	}

	public void setValorPago(float valorPago) {
		this.valorPago = valorPago;
	}

}
