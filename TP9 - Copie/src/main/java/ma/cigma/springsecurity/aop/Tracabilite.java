package ma.cigma.springsecurity.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 
 * SOURCE : le commentaire est lisible uniquement dans le fichier *.java
 * CLASS : Le commentaire est lisible uniquement dans le fichier *.java et le fichie *.class
 * RUNTIME : Le commentaire est lisible dans le fichier *.java, *.class et dans l'exécution.
 *
 */
public @interface Tracabilite {
}
