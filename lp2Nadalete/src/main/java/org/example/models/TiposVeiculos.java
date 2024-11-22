package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPOS_VEICULOS")
public class TiposVeiculos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tveiId;
	
	private String tveiNome;

	public Integer getTveiId() {
		return tveiId;
	}

	public void setTveiId(Integer tveiId) {
		this.tveiId = tveiId;
	}

	public String getTveiNome() {
		return tveiNome;
	}

	public void setTveiNome(String tveiNome) {
		this.tveiNome = tveiNome;
	}
	
	

}
