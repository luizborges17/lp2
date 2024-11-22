package org.example.dtos;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
@Serdeable
@Introspected
public class ClienteVeiculoForm {
	
	private Integer veiculo;
	
	private String cpfCliente;
	
	private Double valorLance;

	public Integer getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Integer veiculo) {
		this.veiculo = veiculo;
	}

	

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Double getValorLance() {
		return valorLance;
	}

	public void setValorLance(Double valorLance) {
		this.valorLance = valorLance;
	}
	
	

}
