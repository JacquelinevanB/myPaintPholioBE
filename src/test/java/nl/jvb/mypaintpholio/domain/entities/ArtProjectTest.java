package nl.jvb.mypaintpholio.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ArtProjectTest {

    private ArtProject artProject;

    @BeforeEach
    void setUp() {
        artProject = new ArtProject();
    }

    @Test
    @DisplayName("Return should be a Long Id of the ArtProject")
    void getIdShouldReturnLongIdOfArtProject() {
        artProject.setId(15L);
        assertEquals(15L, artProject.getId());
    }

    @Test
    @DisplayName("Return should be a String MediumType of the ArtProject")
    void getMediumTypeShouldReturnStringMediumTypeOfArtProject() {
        artProject.setMediumType("testmedium");
        assertEquals("testmedium", artProject.getMediumType());
    }

    @Test
    @DisplayName("Return should be int Height of the ArtProject")
    void getHeightShouldReturnIntHeightOfArtProject() {
        artProject.setHeight(80);
        assertEquals(80, artProject.getHeight());
    }

    @Test
    @DisplayName("Return should be a String Description of the ArtProject")
    void getDescriptionShouldReturnStringDescriptionOfArtProject() {
        artProject.setDescription("Testdescription");
        assertEquals("Testdescription", artProject.getDescription());
    }

    @Test
    @DisplayName("Return should be true when Finished status is set to true")
    void getFinishedShouldReturnTrueWhenBooleanFinishedIsSetToTrue() {
        artProject.setFinished(true);
        assertTrue(artProject.isFinished());
    }

    @Test
    @DisplayName("Should set Finished status to false when false is given")
    void setFinishedShouldSetBooleanFinishedToFalseWhenFalseIsGiven() {
        artProject.setFinished(false);
        assertFalse(artProject.isFinished());
    }

    @Test
    @DisplayName("Should set Title of ArtProject to the input String value")
    void setTitleShouldSetTitleOfArtProjectToInputStringValue() {
        artProject.setTitle("Test title");
        assertEquals("Test title", artProject.getTitle());
    }

    @Test
    @DisplayName("Should set Inspiration of ArtProject to the input String value")
    void setInspirationShouldSetInspirationOfArtProjectToInputStringValue() {
        artProject.setInspiration("Test inspiration");
        assertEquals("Test inspiration", artProject.getInspiration());
    }

    @Test
    @DisplayName("Should set Width of ArtProject to int input")
    void setWidthShouldSetWidthOfArtProjectToInputIntValue() {
        artProject.setWidth(80);
        assertEquals(80, artProject.getWidth());
    }

    @Test
    @DisplayName("Should set Subject of ArtProject to the input String value")
    void setSubjectShouldSetSubjectOfArtProjectToInputStringValue() {
        artProject.setSubject("Test subject");
        assertEquals("Test subject", artProject.getSubject());
    }

    @Test
    @DisplayName("Should set User of ArtProject to the given User")
    void setUser() {
        User testUser = new User();
        artProject.setUser(testUser);
        assertEquals(testUser, artProject.getUser());
    }
}