package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Triangle class
 */
public class Triangle extends Polygon{

    /**
     * Constructor of triangle
     * @param vertices
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

}
