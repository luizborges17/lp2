package org.example.dtos;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
@Serdeable
@Introspected
public class ClienteDispositivoInformaticaForm {
	
	private Double valor;
	
	private String cpfCliente;
	
	private Integer dispositivoInformatica;

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Integer getDispositivoInformatica() {
		return dispositivoInformatica;
	}

	public void setDispositivoInformatica(Integer dispositivoInformatica) {
		this.dispositivoInformatica = dispositivoInformatica;
	}
	
	
}
