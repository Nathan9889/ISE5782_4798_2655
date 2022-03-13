package unittests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    /**
     *test for {@link geometries.Plane#getNormal(Point)}
     */
    @Test
    void getNormal() {

        Sphere sp = new Sphere( new Point(0, 0, 1),1 );
        var normal = sp.getNormal(new Point(1, 0, 1));
        assertEquals(new Vector(1,0,0), normal);
    }
}