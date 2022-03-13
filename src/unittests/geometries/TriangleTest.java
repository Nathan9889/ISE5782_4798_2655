package unittests.geometries;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void testGetNormal() {
        Triangle t = new Triangle(
                new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        double _sqrt = Math.sqrt(1d/3);
        assertEquals(new Vector(_sqrt,_sqrt,_sqrt), t.getNormal(new Point(0,0,1)),"Bad normal");

    }
}