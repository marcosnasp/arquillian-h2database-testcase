package org.example.impls;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.example.entity.Configuracao;
import org.example.entity.Servico;
import org.example.entity.Situacao;
import org.example.interfaces.IConfiguracaoRepository;

@Stateless
public class ConfiguracaoRepository implements IConfiguracaoRepository, Serializable {

    private static final long serialVersionUID = 7120772359665765904L;

    @Inject
    private CrudServiceBean<Configuracao> crudConfiguracao;

    public void cadastrar(Configuracao configuracao) {

        if (configuracao != null) {
            if (configuracao.getConfiguracaoId() == null) {
                configuracao.setSituacao(Situacao.VISIVEL);
                crudConfiguracao.create(configuracao);
            } else {
                throw new IllegalArgumentException("Configuracao para cadastro é inválido");
            }
        } else {
            throw new IllegalArgumentException("Configuracao para cadastro é inválido");
        }

    }

    public Configuracao atualizar(Configuracao configuracao) {

        if (configuracao != null && configuracao.getConfiguracaoId() != null) {
            return crudConfiguracao.update(configuracao);
        } else {
            throw new IllegalArgumentException("Configuracao para atualização é inválido");
        }

    }

    public void remover(Configuracao configuracao) {

        if (configuracao != null && configuracao.getConfiguracaoId() != null) {
            Configuracao configuracaoParaRemocao = crudConfiguracao.find(Configuracao.class, configuracao.getConfiguracaoId());
            configuracaoParaRemocao.setSituacao(Situacao.INVISIVEL);
            crudConfiguracao.update(configuracaoParaRemocao);
        } else {
            throw new IllegalArgumentException("Configuracao para remocao é invalido");
        }

    }

    public Configuracao buscarConfiguracaoPorId(int id) {
        return crudConfiguracao.find(Configuracao.class, id);
    }

    public List<Configuracao> buscarTodas() {
        return crudConfiguracao.findWithNamedQuery(Configuracao.class, "Configuracao.buscarTodas");
    }

    public List<Configuracao> buscarConfiguracoesRelatorioTestes() {

        final String codigoServicoReservado = "00";
        final String nomeServico = "RESERVADO";

        CriteriaBuilder cb = crudConfiguracao.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Configuracao> query = cb.createQuery(Configuracao.class);
        Root<Configuracao> configuracao = query.from(Configuracao.class);
        Join<Configuracao, Servico> servico = configuracao.join("servico", JoinType.LEFT);
        query.where(cb.notEqual(servico.get("codigoServico"), codigoServicoReservado)).where(
                cb.notEqual(servico.get("servico"), nomeServico));

        TypedQuery<Configuracao> configuracaoQuery = crudConfiguracao.getEntityManager().createQuery(query);
        return configuracaoQuery.getResultList();

    }

    public Configuracao buscarConfiguracaoPorChave(Configuracao configuracao) {

        if (configuracao != null) {
            List<Configuracao> ConfiguracaosEncontrados = crudConfiguracao.findWithNamedQuery(Configuracao.class,
                    "Configuracao.buscarPorChave",
                    QueryParameter.with("chave", configuracao.getChave()).and("situacao", configuracao.getSituacao())
                            .parameters());
            if (ConfiguracaosEncontrados.size() == 1) {
                return ConfiguracaosEncontrados.get(0);
            } else {
                return new Configuracao();
            }
        } else {
            throw new IllegalArgumentException("Chave para a busca por Configuracao é inválida");
        }

    }

    public List<Configuracao> buscarConfiguracaoPorFiltros(Configuracao configuracao) {

        return crudConfiguracao.findWithNamedQuery(Configuracao.class, "Configuracao.buscarPorFiltros",
                QueryParameter.with("chave", "%" + configuracao.getChave() + "%").and("situacao", configuracao.getSituacao())
                        .parameters());
    }

    public Map<String, String> getMapaConfiguracoes(List<Configuracao> configuracoes) {

        Map<String, String> configuracoesChaveValor = new HashMap<String, String>();
        if (configuracoes != null && !configuracoes.isEmpty()) {
            for (Configuracao configuracao : configuracoes) {
                // desconsiderar as configurações de ipServidor e portaServidor que pertencem ao servico reservado.
                if (!configuracao.getServico().equals("RESERVADO")) {
                    configuracoesChaveValor.put(configuracao.getChave(), configuracao.getValor());
                }
            }
            return configuracoesChaveValor;
        }
        return configuracoesChaveValor;
    }

}
