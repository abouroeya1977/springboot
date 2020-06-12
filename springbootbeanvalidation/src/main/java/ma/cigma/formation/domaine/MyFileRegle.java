package ma.cigma.formation.domaine;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * SOURCE : commentaire visible uniquement dans le fichier *.java
 * CLASS : commentaire visible uniquement dans le fichier *.class
 * RUNTIME : commentaire visible dans le fichier *.java, *.class et l'exécution
 *
 */
@Constraint(validatedBy=MyFileValidator.class)
public @interface MyFileRegle {

}
