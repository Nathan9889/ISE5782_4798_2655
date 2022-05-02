package unittests.geometries;

import geometries.Intersectable;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    Sphere sphere = new Sphere(new Point(1, 0, 0),1d );

    /**
     *test for {@link geometries.Plane#getNormal(Point)}
     */
    @Test
    void getNormal() {

        Sphere sp = new Sphere( new Point(0, 0, 1),1 );
        var normal = sp.getNormal(new Point(1, 0, 1));
        assertEquals(new Vector(1,0,0), normal);
    }

    /**
     *tests for {@link Intersectable#findIntersections(Ray)}.
     */

    @Test
    void findIntersections1() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray( new Vector(1, 1, 0),new Point(-1, 0, 0))),
                "Ray's line out of sphere");

    }


    @Test
    void findIntersections2(){

        // TC02: Ray starts before and crosses the sphere (2 points)

        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0),
                p2 = new Point(1.53484692283495, 0.844948974278318, 0);

        Ray ray = new Ray(new Vector(3, 1, 0),new Point(-1, 0, 0));
        var result = sphere.findIntersections(ray);

        assertEquals(2, result.size(), "Wrong number of points");

        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");
    }


    @Test
    void findIntersections3(){
        // TC03: Ray starts inside the sphere (1 point)

        Ray ray = new Ray( new Vector(1.5,0,0),new Point(0.5,0,0));
        assertEquals(List.of(new Point(2,0,0)), sphere.findIntersections(ray));
    }


    @Test
    void findIntersections4(){
        // TC04: Ray starts after the sphere (0 points)
        Ray ray = new Ray(new Vector(1,0,0),new Point(3,0,0));
        assertNull(sphere.findIntersections(ray));
    }

    // =============== Boundary Values Tests ==================//

    // **** Group: Ray's line crosses the sphere (but not the center)

    @Test
    void findIntersections5(){
        // TC05: Ray starts at sphere and goes inside (1 points)

        Ray ray = new Ray( new Vector(2,2,0),new Point(0,0,0));
        assertEquals(List.of(new Point(1,1,0)) ,sphere.findIntersections(ray));
    }


    @Test
    void findIntersections6(){
        // TC06: Ray starts at sphere and goes outside (0 points)
        Ray ray = new Ray( new Vector(2,2,0),new Point(1,1,0));
        assertNull(sphere.findIntersections(ray));
    }

    // **** Group: Ray's line goes through the center
    @Test
    void findIntersections7(){
        // TC07: Ray starts before the sphere (2 points)
        Ray ray = new Ray(new Vector(2,2,0), new Point(0,-2,0));
        var result = sphere.findIntersections(ray);
        assertEquals(2, result.size());
    }


    @Test
    void findIntersections8() {
        // TC08: Ray starts at sphere and goes inside (1 points)
        Ray ray = new Ray(new Vector(-1, 0, 0),new Point(2, 0, 0));
        assertEquals(List.of(new Point(0, 0, 0)), sphere.findIntersections(ray));
    }

    @Test
    void findIntersections9() {
        // TC09: Ray starts inside (1 points)
        Ray ray = new Ray( new Vector(-0.59, 0, 0),new Point(0.59, 0, 0));
        assertEquals(List.of(new Point(0, 0, 0)), sphere.findIntersections(ray));
    }

    @Test
    void findIntersections10(){
        // TC10: Ray starts at the center (1 points)
        Ray ray = new Ray( new Vector(2,2,0),new Point(1,0,0));
        assertThrows(IllegalArgumentException.class, ()-> sphere.findIntersections(ray));
    }

    @Test
    void findIntersections11(){
        // TC11: Ray starts at sphere and goes outside (0 points)
        Ray ray = new Ray( new Vector(2,0,0),new Point(2,0,0));
        assertNull(sphere.findIntersections(ray));
    }

    @Test
    void findIntersections12(){
        // TC12: Ray starts after sphere (0 points)
        Ray ray = new Ray (new Vector(1,0,0),new Point(3,0,0));
        assertNull(sphere.findIntersections(ray));
    }

    // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
    @Test
    void findIntersections13(){
        // TC13: Ray starts before the tangent point
        Ray ray = new Ray( new Vector(-1,-1,0),new Point(2,1,1));
        assertNull(sphere.findIntersections(ray));
    }

    @Test
    void findIntersections14(){
        // TC14: Ray starts at the tangent point
        Ray ray = new Ray(new Vector(1,1,0),new Point(1,0,1));
        assertNull(sphere.findIntersections(ray));
    }


    @Test
    void findIntersections15(){
        // TC15: Ray starts after the tangent point
        Ray ray = new Ray( new Vector(4,4,0),new Point(2,1,1));
        assertNull(sphere.findIntersections(ray));
    }

    // **** Group: Special cases
    @Test
    void findIntersections16(){
        // TC16: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        Ray ray = new Ray(new Vector(0,0,1),new Point(3,0,0));
        assertNull(sphere.findIntersections(ray));
    }









}