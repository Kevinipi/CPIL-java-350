package com.ipiecoles.java.java350.model;
import com.ipiecoles.java.java350.exception.EmployeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import nl.jqno.equalsverifier.EqualsVerifier;
import java.time.DayOfWeek;
import java.time.LocalDate;

class EmployeTest {

    //Test unitaire sur la méthode getNombreAnneeAncienneté
    @Test
    void getNombreAnneeAnciennete(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());

        //when
        Integer nbAnneeAciennete = employe.getNombreAnneeAnciennete();

        //then
        Assertions.assertThat(nbAnneeAciennete).isEqualTo(0);
    }


    /**
     * Test nombre d'année d'ancienneté pour la date d'embauche du jour
     */
    @Test
    void testAnneeAncienneteNow(){
        //Given Envoie de la class Employe
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());

        //When  recupéreration du nombre d'année d'ancienneté
        Integer nbAnneEmploye = employe.getNombreAnneeAnciennete();

        //Then : test du nombre d'année d'ancienneté
        Assertions.assertThat(nbAnneEmploye).isEqualTo(0);
    }

    /**
     * Nombre d'année d'ancienneté égal à deux
     */
    @Test
    void getNombreAnneeAncienneteNminus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().minusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete).isEqualTo(2);
    }

    /**
     * Nombre d'année d'ancienneté égal à null
     */
    @Test
    void getNombreAnneeAncienneteNull(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(null);

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete).isEqualTo(0);
    }

    @Test
    void getNombreAnneeAncienneteNplus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().plusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete).isEqualTo(0);
    }

    //Test unitaire nombre d'année d'ancienneté null

    @Test
    void testNbAnneeAncienneteNull(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(null);

        //When
        Integer nbAnnees = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnnees).isEqualTo(0);
    }

    //Test pour testé la méthode hashCode et equals
    @Test
    void equalsHashCodeContracts() {
        EqualsVerifier.forClass(Employe.class).verify();
    }

}
