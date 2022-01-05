package controller.command;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.interfaces.Region;
import model.interfaces.UserChoices;
import model.persistence.UserChoicesImpl;
import model.picture.RegionImpl;
import org.junit.jupiter.api.BeforeEach;
import view.picture.PictureImpl;
import model.picture.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateShapeCommandTest {

  private UserChoices choices;
  private PictureImpl picture;
  private Region region;
  @BeforeEach
  void setup() {
    picture = new PictureImpl();
    choices = new UserChoicesImpl(null);
    region = new RegionImpl(new Point(0,0), new Point(1,1));
  }
  @Test
  @DisplayName("Running CreateShapeCommand should add an element to the picture")
  void verifyCommandAddsToPicture() {
    int startingSize = picture.size();
    CreateShapeCommand c = new CreateShapeCommand(choices, null, picture, region);
    c.run();
    assertAll(
        () -> assertEquals(0, startingSize),
        () -> assertEquals(1, picture.size())
    );
  }

  @Test
  @DisplayName("Executing undo should remove from the picture")
  void verifyUndoRemoves() {
    CreateShapeCommand c = new CreateShapeCommand(choices, null, picture, region);
    c.run();
    c.undo();
    assertEquals(0, picture.size());
  }

  @Test
  @DisplayName("Executing redo should return the element to the Picture")
  void verifyRedoAddsBackIn() {
    CreateShapeCommand c = new CreateShapeCommand(choices, null, picture, region);
    c.run();
    c.undo();
    c.redo();
    assertEquals(1, picture.size());
  }
}
