package nl.jvb.mypaintpholiobe.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

class ArtProjectTest {

    private ArtProject project;

    @BeforeEach
    void setUp() {
        project = new ArtProject();
    }

    @Test
    @DisplayName("Return should be a Long Id of the ArtProject")
    void getIdShouldReturnLongIdOfArtProject() {
        project.setId(15L);
        assertEquals(15L, project.getId());
    }

    @Test
    @DisplayName("Return should be a String MediumType of the ArtProject")
    void getMediumTypeShouldReturnStringMediumTypeOfArtProject() {
        project.setMediumType("testmedium");
        assertEquals("testmedium", project.getMediumType());
    }

    @Test
    @DisplayName("Return should be int Height of the ArtProject")
    void getHeightShouldReturnIntHeightOfArtProject() {
        project.setHeight(80);
        assertEquals(80, project.getHeight());
    }

    @Test
    @DisplayName("Return should be a String Description of the ArtProject")
    void getDescriptionShouldReturnStringDescriptionOfArtProject() {
        project.setDescription("Testdescription");
        assertEquals("Testdescription", project.getDescription());
    }

    @Test
    @DisplayName("Return should be true when Finished status is set to true")
    void getFinishedShouldReturnTrueWhenBooleanFinishedIsSetToTrue() {
        project.setFinished(true);
        assertTrue(project.getFinished());
    }

    @Test
    @DisplayName("Should set Finished status to false when false is given")
    void setFinishedShouldSetBooleanFinishedToFalseWhenFalseIsGiven() {
        project.setFinished(false);
        assertFalse(project.getFinished());
    }

    @Test
    @DisplayName("Should set Title of ArtProject to the input String value")
    void setTitleShouldSetTitleOfArtProjectToInputStringValue() {
        project.setTitle("Test title");
        assertEquals("Test title", project.getTitle());
    }

    @Test
    @DisplayName("Should set Inspiration of ArtProject to the input String value")
    void setInspirationShouldSetInspirationOfArtProjectToInputStringValue() {
        project.setInspiration("Test inspiration");
        assertEquals("Test inspiration", project.getInspiration());
    }

    @Test
    @DisplayName("Should set Width of ArtProject to int input")
    void setWidthShouldSetWidthOfArtProjectToInputIntValue() {
        project.setWidth(80);
        assertEquals(80, project.getWidth());
    }

    @Test
    @DisplayName("Should set Subject of ArtProject to the input String value")
    void setSubjectShouldSetSubjectOfArtProjectToInputStringValue() {
        project.setSubject("Test subject");
        assertEquals("Test subject", project.getSubject());
    }

    @Test
    @DisplayName("Should set User of ArtProject to the given User")
    void setUser() {
        User testUser = new User();
        project.setUser(testUser);
        assertEquals(testUser, project.getUser());
    }
}