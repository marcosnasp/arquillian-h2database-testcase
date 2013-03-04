package org.example.qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Um Qualifier utilizado como ponto de injeção do EntityManager juntamente com
 * as configurações de Contexto de Persistência. Ele é utilizado em CrudServiceBean.
 * Veja um exemplo de codigo:
 * 
 * <pre>
 *  {@code
 *      private @Inject @DataRepository EntityManager entityManager; 
 *  }
 * </pre>
 * 
 * @author <a href="mailto:marcosportela@ioct.com.br">marcosportela@ioct.com.br</a>
 */

@Qualifier
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataRepository {}
