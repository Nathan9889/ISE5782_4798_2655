package unittests.geometries;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

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



    /**
     *tests for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    // ============ Equivalence Partitions Tests ==============
    //TC01: Inside polygon/triangle(1 Point)
    @Test
    void findIntersections1() {
        Triangle triangle = new Triangle(
                new Point(2, 0, 0),
                new Point(0, 3, 0),
                new Point(0, 0, 0));

        Ray ray = new Ray( new Vector(1, 1, 1),new Point(0, 0, -1));
        List<Point> result = triangle.findIntersections(ray);
        Point p1 = new Point(1, 1, 0);
        assertEquals(List.of(p1), result, "Inside polygon/triangle(1 Point)");
    }

    //TC02: Outside against edge(0 Point)
    @Test
    void findIntersections2() {
        Triangle triangle = new Triangle(
                new Point(2, 0, 0),
                new Point(0, 3, 0),
                new Point(0, 0, 0));
        Ray ray = new Ray( new Vector(2, 1, 1),new Point(0, 0, -1));
        assertNull(triangle.findIntersections(ray), "Outside against edge");
    }

    //TC03: Outside against vertex(0 Point)
    @Test
    void findIntersections3() {
        Triangle triangle = new Triangle(
                new Point(2, 0, 0),
                new Point(0, 3, 0),
                new Point(0, 0, 0));
        Ray ray = new Ray( new Vector(3, -0.5, 1),new Point(0, 0, -1));
        assertNull(triangle.findIntersections(ray), "Outside against vertex");
    }

    // =============== Boundary Values Tests ==================
    //****Three cases (the ray begins "before" the plane)**

    //TC04: On edge(0 Point)
    @Test
    void findIntersections4() {
        Triangle triangle = new Triangle(
                new Point(2, 0, 0),
                new Point(0, 3, 0),
                new Point(0, 0, 0));
        Ray ray = new Ray(new Vector(0, 1, 1),new Point(0, 0, -1));
        assertNull(triangle.findIntersections(ray), "On edge");
    }

    //TC05: In vertex(0 Point)
    @Test
    void findIntersections5() {
        Triangle triangle = new Triangle(
                new Point(2, 0, 0),
                new Point(0, 3, 0),
                new Point(0, 0, 0));
        Ray ray = new Ray(new Vector(0, 3, 1),new Point(0, 0, -1));
        assertNull(triangle.findIntersections(ray), "In vertex");
    }

    //TC06: On edge's continuation(0 Point)
    @Test
    void findIntersections6() {
        Triangle triangle = new Triangle(
                new Point(2, 0, 0),
                new Point(0, 3, 0),
                new Point(0, 0, 0));
        Ray ray6 = new Ray(new Vector(0, 4, 1), new Point(0, 0, -1));
        assertNull(triangle.findIntersections(ray6), "On edge's continuation");
    }



}