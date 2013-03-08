package org.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "servico")
public class Servico implements Serializable {

    private static final long serialVersionUID = 3129365974531888190L;

    private Integer servicoId;
    private String codigoServico;
    private String servico;
    private Situacao situacao;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getServicoId() {
        return servicoId;
    }

    public void setServicoId(Integer servicoId) {
        this.servicoId = servicoId;
    }

    @Column
    @NotNull
    public String getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }

    @Column
    @NotNull
    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

}
