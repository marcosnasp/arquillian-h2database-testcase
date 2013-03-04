package org.example.impls;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe auxiliar utilizada pelas classes de repositorio para anexar
 * parametros do tipo Map<String, Object> para alimentar os parametros
 * das NamedQuery.<br /><br />
 * 
 * <b>Veja os metodos que realizam operacoes no banco de dados:
 * <br />
 * exemplo:
 * <br />
 * <br />
 * crudServiceInstance.findWithNamedQuery(Class.class, "Class.namedQueryName",
 * Query.with("nomeNamedQueryName", valor).parameters());
 * 
 * <br />
 * <b>
 * @see org.megatech.horus.bean.repository.BaseOperacionalRepository
 * 
 * 
 * @author <a href="mailto:marcosportela@ioct.com.br">Marcos Portela</a>
 *
 */
public class QueryParameter {
    
    private Map<String, Object> parameters = null;
    
    private QueryParameter(String name,Object value){
        this.parameters = new HashMap<String, Object>();
        this.parameters.put(name, value);
    }
    
    public static QueryParameter with(String name, Object value){
        return new QueryParameter(name, value);
    }
    
    public QueryParameter and(String name, Object value){
        this.parameters.put(name, value);
        return this;
    }
    
    public Map<String, Object> parameters(){
        return this.parameters;
    }
    
}