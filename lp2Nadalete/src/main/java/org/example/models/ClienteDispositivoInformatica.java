package org.example.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CLIENTE_DISPOSITIVO_INFORMATICA")
public class ClienteDispositivoInformatica {
	
	
	
	public ClienteDispositivoInformatica(Double clidiValorLance, Cliente cliente,
			DispositivoInformatica dispositivoInformatica, LocalDateTime clidiDataHoraLance) {
		super();
		this.clidiValorLance = clidiValorLance;
		this.cliente = cliente;
		this.dispositivoInformatica = dispositivoInformatica;
		this.clidiDataHoraLance = clidiDataHoraLance;
	}

	public ClienteDispositivoInformatica() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clidiId;
	
	private Double clidiValorLance;
	
	private LocalDateTime clidiDataHoraLance;
	
	@ManyToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "dispositivo_informatica")
	private DispositivoInformatica dispositivoInformatica;

	public int getClidiId() {
		return clidiId;
	}

	public void setClidiId(int clidiId) {
		this.clidiId = clidiId;
	}

	public Double getClidiValorLance() {
		return clidiValorLance;
	}

	public void setClidiValorLance(Double clidiValorLance) {
		this.clidiValorLance = clidiValorLance;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public DispositivoInformatica getDispositivoInformatica() {
		return dispositivoInformatica;
	}

	public void setDispositivoInformatica(DispositivoInformatica dispositivoInformatica) {
		this.dispositivoInformatica = dispositivoInformatica;
	}

	public LocalDateTime getClidiDataHoraLance() {
		return clidiDataHoraLance;
	}

	public void setClidiDataHoraLance(LocalDateTime clidiDataHoraLance) {
		this.clidiDataHoraLance = clidiDataHoraLance;
	}
	
	
	
	
	
}
