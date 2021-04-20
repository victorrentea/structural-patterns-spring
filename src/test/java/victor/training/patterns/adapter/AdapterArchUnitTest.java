package victor.training.patterns.adapter;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;
import org.junit.Test;

public class AdapterArchUnitTest {
   @Test
   public void dependencyInversionTest() {
      JavaClasses classes = new ClassFileImporter().importPackages("victor.training");

      ClassesShouldConjunction domainDoesnDependOnInfra = ArchRuleDefinition.noClasses()
          .that().resideInAPackage("..adapter.domain")
          .should()
          .dependOnClassesThat().resideInAPackage("..adapter.infra");

      domainDoesnDependOnInfra.check(classes);
   }
}
