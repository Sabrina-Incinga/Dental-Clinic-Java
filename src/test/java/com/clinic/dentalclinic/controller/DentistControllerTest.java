package com.clinic.dentalclinic.controller;

import com.clinic.dentalclinic.dto.DentistDto;
import com.clinic.dentalclinic.exceptions.ResourceNotFoundException;
import com.clinic.dentalclinic.model.Dentist;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistControllerTest {


    private final DentistController controller;

    @Autowired
    DentistControllerTest(DentistController controller) {
        this.controller = controller;
    }


    @Test
    void createNewDentist_shouldReturn201() {
        DentistDto dentistToCreate = new DentistDto();
        dentistToCreate.setFirstName("Pedro");
        dentistToCreate.setLastName("Picapiedra");
        dentistToCreate.setMedicalLicenceNumber("AB2548");

        Dentist createdDentist = controller.createNewDentist(dentistToCreate).getBody();

        Long expectedId = 1L;

        Long actualId = createdDentist.getId();

        assertEquals(expectedId, actualId);

    }

    @Test
    void getAllDentists() {
        DentistDto dentistToCreate = new DentistDto();
        dentistToCreate.setFirstName("Pedro");
        dentistToCreate.setLastName("Picapiedra");
        dentistToCreate.setMedicalLicenceNumber("AB2548");

        controller.createNewDentist(dentistToCreate);

        int expectedSizeOfList = 1;

        int actualSizeOfList = controller.getAllDentists().getBody().size();

        assertEquals(expectedSizeOfList, actualSizeOfList);
    }

    @Test
    void getDentistById() {
        DentistDto dentistToCreate = new DentistDto();
        dentistToCreate.setFirstName("Pedro");
        dentistToCreate.setLastName("Picapiedra");
        dentistToCreate.setMedicalLicenceNumber("AB2548");

        Dentist createdDentist = controller.createNewDentist(dentistToCreate).getBody();

        String expectedLicenceNumber = "AB2548";

        String actualLicenceNumber = createdDentist.getMedicalLicenceNumber();

        assertEquals(expectedLicenceNumber, actualLicenceNumber);

    }

    @Test
    void updateDentist() throws ResourceNotFoundException {
        DentistDto dentistToCreate = new DentistDto();
        dentistToCreate.setFirstName("Pedro");
        dentistToCreate.setLastName("Picapiedra");
        dentistToCreate.setMedicalLicenceNumber("AB2548");

        controller.createNewDentist(dentistToCreate);

        DentistDto updateDentistInfo = new DentistDto();
        updateDentistInfo.setFirstName("Vilma");
        updateDentistInfo.setLastName("Picapiedra");
        updateDentistInfo.setMedicalLicenceNumber("AC5487");

        String expectedNewLicenceNumber = "AC5487";

        String actualNewLicenceNumber = controller.updateDentist(1L, updateDentistInfo).getBody().getMedicalLicenceNumber();


        assertEquals(expectedNewLicenceNumber, actualNewLicenceNumber);

    }

    @Test
    void deleteDentistById() throws ResourceNotFoundException {
        DentistDto dentistToCreate = new DentistDto();
        dentistToCreate.setFirstName("Pedro");
        dentistToCreate.setLastName("Picapiedra");
        dentistToCreate.setMedicalLicenceNumber("AB2548");

        controller.createNewDentist(dentistToCreate);

        int expectedDentistListSize = 0;

        controller.deleteDentistById(1L);

        int actualDentistListSize = controller.getAllDentists().getBody().size();

        assertEquals(expectedDentistListSize, actualDentistListSize);
    }
}