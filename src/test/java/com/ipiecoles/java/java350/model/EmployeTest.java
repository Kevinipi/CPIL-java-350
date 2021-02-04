package com.ipiecoles.java.java350.model;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        Assertions.assertThat(nbAnneeAciennete).isZero();
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
        Assertions.assertThat(nbAnneEmploye).isZero();
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
        Assertions.assertThat(anneeAnciennete).isZero();
    }

    @Test
    void getNombreAnneeAncienneteNplus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().plusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete).isZero();
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
        Assertions.assertThat(nbAnnees).isZero();
    }

    //Test paramétré sur la méthode getPrimeAnnuelle

    @ParameterizedTest
    @CsvSource({
            "1, 'T12345', 0, 1.0, 1000.0",
            "1, 'T12345', 2, 0.5, 600.0",
            "1, 'T12345', 2, 1.0, 1200.0",
            "2, 'T12345', 0, 1.0, 2300.0",
            "2, 'T12345', 1, 1.0, 2400.0",
            "1, 'M12345', 0, 1.0, 1700.0",
            "1, 'M12345', 5, 1.0, 2200.0",
            "2, 'M12345', 0, 1.0, 1700.0",
            "2, 'M12345', 8, 1.0, 2500.0",
            ", 'M12345',4, 2.0, 4200.0",
            "2,, 4, 1.0, 2700.0"
    })
    void getPrimeAnnuelle(Integer performance, String matricule, Long nbAnneeAnciennete, Double tempsPartiel, Double primeCalculee){
        //Given
        Employe employe = new Employe("Pose", "Kévin", "C00004", LocalDate.now(), 3000.0, 1, 3.0 );
        employe.setMatricule(matricule);
        employe.setTempsPartiel(tempsPartiel);
        employe.setDateEmbauche(LocalDate.now().minusYears(nbAnneeAnciennete));
        employe.setPerformance(performance);
        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(primeCalculee);

    }

    //Deuxième test paramétré sur la méthode getPrimeAnuelle

    @ParameterizedTest
    @CsvSource({
            "2, 'M12345',4, 1.0, 3970.0",
    })
    void getPrimeAnnuelle2(Integer performance, String matricule, Long nbAnneeAnciennete, Double tempsPartiel, Double primeCalculee){
        //Given
        Employe employe = new Employe();
        employe.setMatricule(matricule);
        employe.setTempsPartiel(tempsPartiel);
        employe.setDateEmbauche(LocalDate.now().minusYears(nbAnneeAnciennete));
        employe.setPerformance(performance);
        //When
        Double primeAnciennete = Entreprise.PRIME_ANCIENNETE * employe.getNombreAnneeAnciennete();
        Double prime = employe.getPrimeAnnuelle()* Entreprise.INDICE_PRIME_MANAGER + primeAnciennete;
        //Then
        Assertions.assertThat(prime).isEqualTo(primeCalculee);
    }

}
