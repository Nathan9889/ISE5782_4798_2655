package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {

    final Point p;
    final Vector normal;




    /**
     * Constructor of Plane from 3 points
     * we calculate the normal on the constructor to avoid repeatedly request normal
     * @param p1 P1
     * @param p2 P2
     * @param p3 P3
     * @throws IllegalArgumentException if UxV = (0,0,0) => all 3 point on the same line
     */
    public Plane(Point p1, Point p2, Point p3) {

        try {
            Vector U = p2.subtract(p1);
            Vector V = p3.subtract(p1);

            Vector N = U.crossProduct(V);

            normal = N.normalize();

            p = p1;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("can not create a plane with all 3 point on the same line");
        }
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


    public Vector getNormal() {
        return normal;
    }


    /**
     * ovveriding getnormal of geometry
     * @param p point
     * @return normal to the geometry
     */
    @Override
    public Vector getNormal(Point p) {
        return normal;
    }


}
