package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        assertEquals(new Vector(-1, -2, -3), v1.add(v2), "add operation fail");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        assertTrue(isZero((v1.dotProduct(v3))),"ERROR: dotProduct() for orthogonal vectors is not zero" );
        assertTrue(isZero(v1.dotProduct(v2) + 28), "ERROR: dotProduct() wrong value");
    }


    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }


    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    void testScale() {
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(new Vector(1*2,2*2,3*2), v1.scale(2), "Error scale is not working properly");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}
     */
    @Test
    void testLengthSquared() {

        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        assertEquals(14, v1.lengthSquared(), "ERROR: lengthSquared() wrong value\"");
    }


    /**
     * Test method for {@link primitives.Vector#length()}
     */
    @Test
    void testLength() {

        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        assertEquals(5, new Vector(0, 3, 4).length(), "ERROR: length() wrong value");
    }


    /**
     * Test method for {@link primitives.Vector#normalize()}
     */
    @Test
    void testNormalize() {

        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertEquals(u.length() - 1, 0,"ERROR: the normalized vector is not a unit vector");


        try { // test that the vectors are co-lined
            v.crossProduct(u);
            out.println("ERROR: the normalized vector is not parallel to the original one");
        } catch (Exception e) {
        }
        if (v.dotProduct(u) < 0)
            out.println("ERROR: the normalized vector is opposite to the original one");
    }
}