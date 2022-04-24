package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    Ray ray = new Ray( new Vector(-2,-2,-2),new Point(2,-2,3));
    /**
     * {@link primitives.Ray#findClosestPoint(List<Point>)}
     */
    // ============ Equivalence Partitions Tests ==============
    //TC01: Closest point is in the middle of the list
    @Test
    void findClosestGeoPoint() {
        var points = List.of(
                new Point(0,0,1),
                new Point(0,-1,0),
                new Point(1,-2,3),
                new Point(1,1,1),
                new Point(1,2,3));

        assertEquals((points.get(points.size() / 2)), ray.findClosestPoint(points));
    }

    // =============== Boundary Values Tests ==================
    //TC02: gets empty list
    @Test
    void findClosestGeoPoint2() {
        List<Point> points = null;
        assertNull(ray.findClosestPoint(points));
    }

    //TC03: Closest point is in the beginning of the list
    @Test
    void findClosestGeoPoint3() {
        var points = List.of(
                new Point(1,-2,3),
                new Point(0,-1,0),
                new Point(1,-2,3),
                new Point(1,1,1),
                new Point(1,2,3));
        assertEquals(points.get(0), ray.findClosestPoint(points));
    }

    //TC04: Closest point is in the end of the list
    @Test
    void findClosestGeoPoint4() {
        var points = List.of(
                new Point(1,-2,3),
                new Point(0,-1,0),
                new Point(1,-2,3),
                new Point(1,1,1),
                new Point(1,-2,3));
        assertEquals(points.get(points.size()-1), ray.findClosestPoint(points));
    }

}