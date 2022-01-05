package model.picture;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegionImplTest {
  @Test
  @DisplayName("A drag from upper left to lower right should leave start point unchanged")
  void testUpperLeftToLowerRight() {
    Point start = new Point(1,1);
    Point end = new Point(2, 2);
    RegionImpl r = new RegionImpl(start, end);
    assertEquals(start, r.start());
  }

  @Test
  @DisplayName("A drag from lower right to upper left should swap end for start")
  void testLowerRightToUpperLeft() {
    Point start = new Point(1,1);
    Point end = new Point(0, 0);
    RegionImpl r = new RegionImpl(start, end);
    assertEquals(end, r.start());
  }

  @Test
  @DisplayName("A drag from upper right to lower left should swap end for start")
  void testUpperRightToLowerLeft() {
    Point start = new Point(1,1);
    Point end = new Point(0, 2);
    RegionImpl region = new RegionImpl(start, end);
    assertEquals(new Point(0, 1), region.start());
  }

  @Test
  @DisplayName("A drag from upper right to lower left should swap end for start")
  void testLowerLeftToUpperRight() {
    RegionImpl region = makeRegion(1,1, 2, 0);
    assertEquals(new Point(1, 0), region.start());
  }

  private RegionImpl makeRegion(int x1, int y1, int x2, int y2) {
    Point start = new Point(x1,y1);
    Point end = new Point(x2, y2);
    return new RegionImpl(start, end);
  }
}

