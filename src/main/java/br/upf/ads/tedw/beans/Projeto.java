package br.upf.ads.tedw.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * Entity implementation class for Entity: Projeto
 *
 */

@Entity
public class Projeto implements Serializable {

	@Version
	private Integer version;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProjetoId")
	@SequenceGenerator(name = "ProjetoId", sequenceName = "ProjetoId", allocationSize = 1)
	private Long id;

	@NotBlank(message = "Informe o nome do projeto")
	@Length(min = 10, max = 200, message = "O nome do projeto deve ter entre {min} e {max} caracteres")
	@Column(length = 200, nullable = false)
	private String nome;

	@NotBlank(message = "Informe a descrição")
	//@Lob
	@Column(columnDefinition = "text")
	private String descricao;

	@NotNull(message = "Selecione o Usuário")
	@ManyToOne(optional = false)
	private Usuario usuario;

	@NotNull(message = "Selecione o Cliente")
	@ManyToOne(optional = false)
	private Cliente cliente;

	private static final long serialVersionUID = 1L;

	public Projeto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
}
