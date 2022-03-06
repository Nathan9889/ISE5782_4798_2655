package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {

    final Point p;
    final Vector normal;

    /**
     * Constructor of plane using 3 Point
     * @param p1 Point 1
     * @param p2 Point 2
     * @param p3 Point 3
     */
    public Plane(Point p1, Point p2, Point p3) {
        p = p1;
        normal = null;
    }

    /**
     * Constructor of plane using 1 Point and a Normal Vector
     * @param v1
     * @param p1
     */
    public Plane(Vector v1,Point p1){
        p = p1;
        normal  = v1.normalize();
    }


    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    public Vector getNormal() {
        return normal;
    }
}
