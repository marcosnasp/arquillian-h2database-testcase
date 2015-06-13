package org.example.testcase.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

@Stateless
public class StaticProvider implements Serializable {

    private static final long serialVersionUID = 2338688052026165795L;
    private static final Map<String, String> IMPORTS = new HashMap<String, String>();
    private static final Map<String, String> PERSISTENCEDB = new HashMap<String, String>();

    static {
        IMPORTS.put("postgres-import", "src/test/resources/import.sql");
        IMPORTS.put("h2database-import", "src/test/resources/import.sql");
        IMPORTS.put("autenticacao-import", "src/test/resources/imports-for-test/import-autenticacao.sql");
        IMPORTS.put("import-relatorio-testes", "src/test/resources/imports/import-relatorio-testes.sql");
        IMPORTS.put("import-arquivo", "src/test/resources/imports/import-arquivo.sql");
        IMPORTS.put("import-operacaoviaturapessoa", "src/test/resources/imports/import-operacaoviaturapessoa.sql");
        IMPORTS.put("import-operacaosync", "src/test/resources/imports/import-operacaosync.sql");

        PERSISTENCEDB.put(TipoBanco.POSTGRESQL.getTipoBanco(),
            "src/test/resources/resources-jbossas-remote/test-persistence-postgres.xml");
        PERSISTENCEDB.put(TipoBanco.H2DATABASE.getTipoBanco(),
            "src/test/resources/resources-jbossas-remote/test-persistence-in-h2database.xml");
        PERSISTENCEDB.put(TipoBanco.HSQLDB.getTipoBanco(),
            "src/test/resources/resources-jbossas-remote/test-persistence-in-hsqldb.xml");
    }

    public static Map<String, String> getImports() {
        return IMPORTS;
    };

    public static Map<String, String> getPersistenceDescriptors() {
        return PERSISTENCEDB;
    }

    public enum TipoBanco {

        POSTGRESQL("postgres"),
        H2DATABASE("h2database"),
        HSQLDB("hsqldb");

        private String tipoBanco;

        private TipoBanco(String tipoBanco) {
            this.tipoBanco = tipoBanco;
        }

        public String getTipoBanco() {
            return tipoBanco;
        }

    }

}
