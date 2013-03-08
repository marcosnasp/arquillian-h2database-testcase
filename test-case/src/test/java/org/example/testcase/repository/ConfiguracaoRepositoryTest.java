package org.example.testcase.repository;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.example.entity.Configuracao;
import org.example.interfaces.IConfiguracaoRepository;
import org.example.testcase.util.Libraries;
import org.example.testcase.util.ModulesHelper;
import org.example.testcase.util.StaticProvider;
import org.example.testcase.util.StaticProvider.TipoBanco;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
public class ConfiguracaoRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracaoRepositoryTest.class);

    @Inject
    IConfiguracaoRepository configuracaoRepo;

    @Deployment
    public static Archive<?> createArchive() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "ConfiguracaoRepositoryTest.war")
                .addAsLibraries(Libraries.getLogLibraries()).addAsLibraries(Libraries.getCDILibraries())
                .addAsLibraries(Libraries.getDroolsLibraries())
                .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        JavaArchive jar = ((JavaArchive) ModulesHelper.getConfiguracaoRepositoryTest()).addAsManifestResource(
                EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml")).addAsManifestResource(
                new File(StaticProvider.getPersistenceDescriptors().get(TipoBanco.H2DATABASE.getTipoBanco())),
                ArchivePaths.create("persistence.xml"));

        war.addAsLibraries(jar);

        LOGGER.info("ConfiguracaoRepositoryTest.war\n\n {} {}", war.toString(true), "\n\n");
        LOGGER.info("ConfiguracaoRepositoryTest.jar\n\n {} {}", jar.toString(true), "\n\n");

        return war;

    }

    @Test
    public void injectionTest() {
        assertThat(configuracaoRepo, notNullValue());
    }

    @Test
    @Cleanup(phase = TestExecutionPhase.NONE)
    @UsingDataSet({ "servicos.yml", "configuracoes.yml" })
    public void buscarTodasAsConfiguracoes() {
        final List<Configuracao> todasAsConfiguracoes = configuracaoRepo.buscarTodas();
        assertThat(todasAsConfiguracoes, notNullValue());
        assertThat(todasAsConfiguracoes, hasSize(15));
    }

    @Test
    @Cleanup(phase = TestExecutionPhase.NONE)
    public void buscarAsConfiguracoesParaRelatorioDeTestes() {
        final List<Configuracao> configuracoesRelatorio = configuracaoRepo.buscarConfiguracoesRelatorioTestes();
        LOGGER.info("Tamanho configurações relatorio: {}", configuracoesRelatorio.size());
        assertThat(configuracoesRelatorio, notNullValue());
        assertThat(configuracoesRelatorio, hasSize(13));
    }

}
