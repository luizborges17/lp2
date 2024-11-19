package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPOS_DI")
public class TiposDi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tdiId;
	
	private String tdiNome;

	public Integer getTdiId() {
		return tdiId;
	}

	public void setTdiId(Integer tdiId) {
		this.tdiId = tdiId;
	}

	public String getTdiNome() {
		return tdiNome;
	}

	public void setTdiNome(String tdiNome) {
		this.tdiNome = tdiNome;
	}
	
	
}
