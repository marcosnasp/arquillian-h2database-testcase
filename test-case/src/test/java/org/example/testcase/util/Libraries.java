package org.example.testcase.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.jboss.shrinkwrap.resolver.api.maven.filter.StrictFilter;

public class Libraries {

    // Nomes dos artefatos ... sem a versão, utiliza implicitamente a do pom.xml.
    public static final String SLF4J_API = "org.slf4j:slf4j-api";
    public static final String SLF4J_SIMPLE = "org.slf4j:slf4j-simple";

    public static final String JODA_TIME = "joda-time:joda-time";

    public static final String CDI = "javax.enterprise:cdi-api";

    public static final String DROOLS_CORE = "org.drools:drools-core";
    public static final String DROOLS_COMPILE = "org.drools:drools-compiler";

    // localizacao dos arquivos pom.xml
    public static final String TEST_POM_LOCATION = "pom.xml";

    private static Map<String, Collection<JavaArchive>> libs = new HashMap<String, Collection<JavaArchive>>();

    // Nao ha necessidade de ser instanciada...
    private Libraries() {
    }

    public static Collection<JavaArchive> getLogLibraries() {

        final Collection<JavaArchive> libs = new LinkedList<JavaArchive>();
        libs.addAll(getLib(SLF4J_SIMPLE, TEST_POM_LOCATION, true));
        libs.addAll(getLib(SLF4J_API, TEST_POM_LOCATION, false));
        return libs;
    }

    public static Collection<JavaArchive> getCDILibraries() {

        final Collection<JavaArchive> libs = new LinkedList<JavaArchive>();
        libs.addAll(getLib(CDI, TEST_POM_LOCATION, false));
        return libs;

    }

    public static Collection<JavaArchive> getDroolsLibraries() {

        final Collection<JavaArchive> libs = new LinkedList<JavaArchive>();
        libs.addAll(getLib(DROOLS_CORE, TEST_POM_LOCATION, false));
        libs.addAll(getLib(DROOLS_COMPILE, TEST_POM_LOCATION, false));

        return libs;
    }

    /**
     * Inclusão de dependências a partir do pom.xml de qualquer projeto desde que se inclua o caminho para o pom...
     * 
     * @param artifact
     * @param pathToModuleWithPomXML caminho para o pom.xml - exemplo: ../horus-audit/pom.xml ou pom.xml (caso se deseje
     *        carregar do pom.xml do projeto de test)
     * @param strictFilter - se false - retorna as dependências relacionadas indiretamente a selecionada
     * @return
     */
    private static Collection<JavaArchive> getLib(String artifact, String pathToModuleWithPomXML, boolean strictFilter) {

        if (!libs.containsKey(artifact)) {
            MavenDependencyResolver res = DependencyResolvers.use(MavenDependencyResolver.class)
                    .loadMetadataFromPom(pathToModuleWithPomXML).artifact(artifact);
            libs.put(artifact,
                    strictFilter ? res.resolveAs(JavaArchive.class, new StrictFilter()) : res.resolveAs(JavaArchive.class));
        }
        return libs.get(artifact);

    }

}