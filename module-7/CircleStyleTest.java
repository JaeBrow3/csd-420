import org.junit.jupiter.api.Test;
import javafx.scene.shape.Circle;
import static org.junit.jupiter.api.Assertions.*;

public class CircleStyleTest {

    @Test
    void testCircleStyles() {
        Circle c = CircleStyle.createPlainCircle();
        assertTrue(c.getStyleClass().contains("plaincircle"));
        assertTrue(c.getStyleClass().contains("circleborder"));
    }

    @Test
    void testColorIds() {
        Circle red = CircleStyle.createPlainCircle();
        red.setId("redcircle");

        Circle green = CircleStyle.createPlainCircle();
        green.setId("greencircle");

        assertEquals("redcircle", red.getId());
        assertEquals("greencircle", green.getId());
    }
}
