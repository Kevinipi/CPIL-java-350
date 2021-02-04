package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

//Annote la class afin de pouvoir integrer les élements simuler dans la var employeService
@ExtendWith(MockitoExtension.class)
public class EmployeServiceTest {
    @InjectMocks
    EmployeService employeService;

    @Mock //nous allons injecter la dedans ce n'est pas le vrai repository c'est celui crée afin de simuler un test
    EmployeRepository employeRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this.getClass());
    }

    @Test
    void testEmbaucheEmployeTechTempsPleinBTS() throws EmployeException {
        //Given
        String nom = "Doe";
        String prenom = "John";
        Poste poste = Poste.TECHNICIEN;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1.0;
        //Mocker l'appel a findLastMatricule
        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00345");
        //Mocker l'appel a findByMatricule
        Mockito.when(employeRepository.findByMatricule("T00346")).thenReturn(null);
        //When
        employeService.embaucheEmploye(nom, prenom, poste,niveauEtude, tempsPartiel);

        //Then
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository).save(employeArgumentCaptor.capture());
        Employe employe = employeArgumentCaptor.getValue();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getMatricule()).isEqualTo("T00346");
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
    }
}
