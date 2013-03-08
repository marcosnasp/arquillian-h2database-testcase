package org.example.testcase.util;

import java.io.File;

import org.example.entity.Configuracao;
import org.example.entity.ProblemaHorus;
import org.example.entity.Servico;
import org.example.entity.Situacao;
import org.example.impls.ConfiguracaoRepository;
import org.example.impls.CrudServiceBean;
import org.example.impls.QueryParameter;
import org.example.interfaces.CrudService;
import org.example.interfaces.IConfiguracaoRepository;
import org.example.producers.DataRepositoryProducer;
import org.example.qualifiers.DataRepository;
import org.example.testcase.util.StaticProvider.TipoBanco;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public class ModulesHelper {
    
    private ModulesHelper() { }

    public static Archive<?> getConfiguracaoRepositoryTest() {
        return ShrinkWrap
                .create(JavaArchive.class, "ConfiguracaoRepositoryTest.jar")
                .addClasses(ConfiguracaoRepository.class, IConfiguracaoRepository.class)
                .addClasses(Configuracao.class, Servico.class, ProblemaHorus.class, Situacao.class)
                .addClasses(CrudServiceBean.class, CrudService.class, QueryParameter.class, DataRepositoryProducer.class,
                        DataRepository.class);
    }

    public static Archive<?> getConfiguracaoRepositoryTest(final TipoBanco tipoBanco) {
        return ((JavaArchive) getConfiguracaoRepositoryTest())
                .addAsManifestResource(new File(StaticProvider.getPersistenceDescriptors().get(tipoBanco.getTipoBanco())));    
    }
           
}
