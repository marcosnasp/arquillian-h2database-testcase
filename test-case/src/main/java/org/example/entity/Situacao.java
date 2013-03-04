package org.example.entity;


public enum Situacao {
 
	 VISIVEL("VISÍVEL"),
	 
	 INVISIVEL("INVISÍVEL");
	 
	 private String situacao;
	 
	 private Situacao(String situacao) {
		 this.situacao = situacao;
	 }
	 
	 public String getNome() {
		 return situacao;
	 }
	 
}
 
