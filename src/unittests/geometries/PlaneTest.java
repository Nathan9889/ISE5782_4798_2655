package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

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
            fail("constructor create plane with the same line");
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


    /**
     *tests for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    // ============ Equivalence Partitions Tests ==============//
    // The Ray's here ar not orthogonal and not parallels to the plane

    // TC01: Ray intersect the plane (1 point)
    @Test
    void findIntersections1() {
        Plane plane = new Plane(
                new Point(0, 0, 1),
                new Point(0, 2, 0),
                new Point(1, 0, 0));
        Ray ray = new Ray(new Vector(1, 4,-1),new Point(0,-2,0) );
        assertEquals(List.of(new Point(1,2,-1)), plane.findIntersections(ray));
    }

    // TC02: Ray does not intersect the plane (0 point)
    @Test
    void findIntersections2() {
        Plane plane = new Plane(
                new Point(0, 0, 1),
                new Point(0, 2, 0),
                new Point(1, 0, 0));
        Ray ray = new Ray( new Vector(6,-5,0),new Point(0, 5, 0));
        assertNull(plane.findIntersections(ray));
    }

    // =============== Boundary Values Tests ==================

    // **** Group: Ray is orthogonal to the plane
    // TC03: Ray start outside of the plane and goes inside the plane (1 point)
    @Test
    void findIntersections3() {
        Plane plane = new Plane(
                new Point(0, 0, 1),
                new Point(0, 2, 0),
                new Point(1, 0, 0));
        Ray ray = new Ray( new Vector(2,1,2),new Point(-2, 0, 0));
        assertEquals(List.of(new Point(-0.6666666666666667, 0.6666666666666666, 1.3333333333333333)),
                plane.findIntersections(ray));
    }

    // TC04: Ray start outside of the plane (0 point)
    @Test
    void findIntersections4() {
        Plane plane = new Plane(
                new Point(0, 0, 1),
                new Point(0, 2, 0),
                new Point(1, 0, 0));
        Ray ray = new Ray( new Vector(-1.53,-0.77,-1.53),new Point(-2, 0, 0));
        assertNull(plane.findIntersections(ray));
    }

    // TC05: Ray start inside the plane (0 point)
    @Test
    void findIntersections5() {
        Plane plane = new Plane(
                new Point(0, 0, 1),
                new Point(0, 2, 0),
                new Point(1, 0, 0));
        Ray ray = new Ray(new Vector(1,0.5,2),new Point(0, 0, 1));
        assertNull(plane.findIntersections(ray));
    }

    // **** Group: Ray is parallel to the plane
    // TC06: Ray start inside (0 point)
    @Test
    void findIntersections6() {
        Plane plane = new Plane(
                new Point(0, 0, 1),
                new Point(0, 2, 0),
                new Point(1, 0, 0));
        Ray ray = new Ray(new Vector(-1.8,-0.56,2.08),new Point(0.67, -2.16, 1.41));
        assertNull(plane.findIntersections(ray));
    }

    // TC07: Ray start outside (0 point)
    @Test
    void findIntersections7() {
        Plane plane = new Plane(
                new Point(0, 0, 1),
                new Point(0, 2, 0),
                new Point(1, 0, 0));
        Ray ray = new Ray( new Vector(-0.64,-0.2,0.74),new Point(5, 0, 0));
        assertNull(plane.findIntersections(ray));
    }
}