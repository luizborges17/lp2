package org.example.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ENTIDADE_FINANCEIRA")
public class EntidadeFinanceira {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer entfinId;
	
	private String entfinNome;
	
	@ManyToMany(mappedBy = "entidadesFinanceiras")
	private List<Leilao> leiloes;
	
	public EntidadeFinanceira() {}

	public EntidadeFinanceira(String entfinNome) {
		super();
		this.entfinNome = entfinNome;
	}

	public Integer getEntfinId() {
		return entfinId;
	}

	public void setEntfinId(Integer entfinId) {
		this.entfinId = entfinId;
	}

	public String getEntfinNome() {
		return entfinNome;
	}

	public void setEntfinNome(String entfinNome) {
		this.entfinNome = entfinNome;
	}



	public List<Leilao> getLeiloes() {
		return leiloes;
	}



	public void setLeiloes(List<Leilao> leiloes) {
		this.leiloes = leiloes;
	}
	
	
	
	
}
