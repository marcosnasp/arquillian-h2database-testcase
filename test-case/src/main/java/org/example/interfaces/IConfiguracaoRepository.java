package org.example.interfaces;

import java.util.List;
import java.util.Map;

import org.example.entity.Configuracao;


public interface IConfiguracaoRepository {

	public void cadastrar(Configuracao configuracao);
	
	public Configuracao atualizar(Configuracao configuracao);
	
	public void remover(Configuracao configuracao);
	
	public Configuracao buscarConfiguracaoPorId(int id);
	
	public List<Configuracao> buscarTodas();
	
	public List<Configuracao> buscarConfiguracoesRelatorioTestes();
	
	public Configuracao buscarConfiguracaoPorChave(Configuracao configuracao);
	
    public List<Configuracao> buscarConfiguracaoPorFiltros(Configuracao configuracao);
    
    public Map<String, String> getMapaConfiguracoes(List<Configuracao> configuracoes); 

}
