package org.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@NamedQueries({
        @NamedQuery(name = "Configuracao.buscarTodas", query = "SELECT c FROM Configuracao c WHERE c.situacao = 'VISIVEL'"),
        @NamedQuery(name = "Configuracao.buscarPorChave", query = "SELECT c FROM Configuracao c WHERE c.chave = :chave AND c.situacao = :situacao"),
        @NamedQuery(name = "Configuracao.buscarPorFiltros", query = "SELECT c FROM Configuracao c WHERE c.chave LIKE :chave AND c.situacao = :situacao") })
@Entity
@Audited
@Table
public class Configuracao implements Serializable {

    private static final long serialVersionUID = 4121082083992615765L;

    private Integer configuracaoId;
    private String chave;
    private String valor;
    private Servico servico;
    private ProblemaHorus problemaHorus;
    private Situacao situacao;

    public Configuracao() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getConfiguracaoId() {
        return configuracaoId;
    }

    @NotNull
    @Column(unique = true)
    public String getChave() {
        return chave;
    }

    @NotNull
    @Column
    public String getValor() {
        return valor;
    }

    @OneToOne
    @JoinColumn(name = "servico_id_pk")
    public Servico getServico() {
        return servico;
    }

    @Enumerated(EnumType.STRING)
    @NotNull
    public Situacao getSituacao() {
        return situacao;
    }

    @Enumerated(EnumType.STRING)
    public ProblemaHorus getProblemaHorus() {
        return problemaHorus;
    }

    public void setConfiguracaoId(Integer configuracaoId) {
        this.configuracaoId = configuracaoId;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public void setProblemaHorus(ProblemaHorus problemaHorus) {
        this.problemaHorus = problemaHorus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((chave == null) ? 0 : chave.hashCode());
        result = prime * result + ((configuracaoId == null) ? 0 : configuracaoId.hashCode());
        result = prime * result + ((problemaHorus == null) ? 0 : problemaHorus.hashCode());
        result = prime * result + ((servico == null) ? 0 : servico.hashCode());
        result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
        Configuracao other = (Configuracao) obj;
        if (chave == null) {
            if (other.chave != null)
                return false;
        } else if (!chave.equals(other.chave))
            return false;
        if (configuracaoId == null) {
            if (other.configuracaoId != null)
                return false;
        } else if (!configuracaoId.equals(other.configuracaoId))
            return false;
        if (problemaHorus != other.problemaHorus)
            return false;
        if (servico == null) {
            if (other.servico != null)
                return false;
        } else if (!servico.equals(other.servico))
            return false;
        if (situacao != other.situacao)
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

}