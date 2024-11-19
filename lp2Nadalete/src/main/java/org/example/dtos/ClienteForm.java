package org.example.dtos;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
@Serdeable
@Introspected
public class ClienteForm {

	private String cliCpf;
	
	private String cliNome;
	
	private String cliEmail;

	public String getCliCpf() {
		return cliCpf;
	}

	public void setCliCpf(String cliCpf) {
		this.cliCpf = cliCpf;
	}

	public String getCliNome() {
		return cliNome;
	}

	public void setCliNome(String cliNome) {
		this.cliNome = cliNome;
	}

	public String getCliEmail() {
		return cliEmail;
	}

	public void setCliEmail(String cliEmail) {
		this.cliEmail = cliEmail;
	}
	
	
}
