package nl.jvb.mypaintpholio.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
    }

    @Test
    @DisplayName("Return should be a Long Id of the Project")
    void getIdShouldReturnLongIdOfProject() {
        project.setId(15L);
        assertEquals(15L, project.getId());
    }

    @Test
    @DisplayName("Return should be a String MediumType of the Project")
    void getMediumTypeShouldReturnStringMediumTypeOfProject() {
        project.setMediumType("testmedium");
        assertEquals("testmedium", project.getMediumType());
    }

    @Test
    @DisplayName("Return should be int Height of the Project")
    void getHeightShouldReturnIntHeightOfProject() {
        project.setHeight(80);
        assertEquals(80, project.getHeight());
    }

    @Test
    @DisplayName("Return should be a String Description of the Project")
    void getDescriptionShouldReturnStringDescriptionOfProject() {
        project.setDescription("Testdescription");
        assertEquals("Testdescription", project.getDescription());
    }

    @Test
    @DisplayName("Return should be true when Finished status is set to true")
    void getFinishedShouldReturnTrueWhenBooleanFinishedIsSetToTrue() {
        project.setFinished(true);
        assertTrue(project.isFinished());
    }

    @Test
    @DisplayName("Should set Finished status to false when false is given")
    void setFinishedShouldSetBooleanFinishedToFalseWhenFalseIsGiven() {
        project.setFinished(false);
        assertFalse(project.isFinished());
    }

    @Test
    @DisplayName("Should set Title of Project to the input String value")
    void setTitleShouldSetTitleOfProjectToInputStringValue() {
        project.setTitle("Test title");
        assertEquals("Test title", project.getTitle());
    }

    @Test
    @DisplayName("Should set Inspiration of Project to the input String value")
    void setInspirationShouldSetInspirationOfProjectToInputStringValue() {
        project.setInspiration("Test inspiration");
        assertEquals("Test inspiration", project.getInspiration());
    }

    @Test
    @DisplayName("Should set Width of Project to int input")
    void setWidthShouldSetWidthOfProjectToInputIntValue() {
        project.setWidth(80);
        assertEquals(80, project.getWidth());
    }

    @Test
    @DisplayName("Should set Subject of Project to the input String value")
    void setSubjectShouldSetSubjectOfProjectToInputStringValue() {
        project.setSubject("Test subject");
        assertEquals("Test subject", project.getSubject());
    }

    @Test
    @DisplayName("Should set User of Project to the given User")
    void setUser() {
        Person testPerson = new Person();
        project.setPerson(testPerson);
        assertEquals(testPerson, project.getPerson());
    }
}