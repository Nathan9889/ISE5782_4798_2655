package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testAdd() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(new Point(0,0,0), p1.add(new Vector(-1, -2, -3)),
                "ERROR: Point + Vector does not work correctly");
    }

    @Test
    void testSubtract() {
        Point p1 = new Point(1,2,3);
        assertEquals(new Vector(1,1,1), new Point(2,3,4).subtract(p1),
                "ERROR: Point - Point does not work correctly");
    }

    @Test
    void testDistanceSquared() {
        Point p1 = new Point(1,2,3);
        double distance = Math.pow(3-1,2) + Math.pow(6-2,2) + Math.pow(9-3,2);
        assertEquals(distance,p1.distanceSquared(new Point(3,6,9)),0.0001,
                "ERROR: Wrong distance squared calculus");
    }

    @Test
    void testDistance() {
        Point p1 = new Point(1,2,3);
        double distance = Math.sqrt(Math.pow(2-1,2) + Math.pow(4-2,2) + Math.pow(6-3,2));
        assertEquals(distance,p1.distance(new Point(2,4,6)),0.00001,
                "ERROR: Wrong distance squared calculus");
    }
}