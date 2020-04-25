package br.upf.ads.tedw.suport;

public enum Letra {

	LETRA_N("N"), LETRA_F("F");

	private String descricao;

	Letra(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.descricao;
	}
}
