package unittests.geometries;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    /**
     *test for {@link geometries.Tube#getNormal(primitives.Point)}
     */
    @Test
    void getNormal() {
        Ray ray = new Ray( new Vector(0,2,0),new Point(0,1,0));
        Tube tube = new Tube( ray,2);

        assertEquals(new Vector(1,0,0), tube.getNormal(new Point(2,0,0)),"Bad normal");
    }
}