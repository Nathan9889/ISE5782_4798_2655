package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {


    /**
     *test for {@link geometries.Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testPlane() {
        try {
            new Plane(
                    new Point(1, 0, 0),
                    new Point(2, 0, 0),
                    new Point(4, 0, 0));
            fail("constructor crate plane with the same line");
        } catch (IllegalArgumentException e) {
        }
    }


    @Test
    void testGetNormal() {
        Plane plane = new Plane(
                new Point(0, 0, 1),
                new Point(0, 2, 0),
                new Point(1, 0, 0));
        assertEquals(1, plane.getNormal(null).length());
    }


}