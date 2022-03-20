package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane implements Geometry {

    final Point q0;
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

            q0 = p1;
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
        q0 = p1;
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

    /**
     *
     * @param ray {@link Ray} point to object
     * @return list of point that intersect the object
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        Point p0 = ray.getP0();
        Vector v1 = ray.getDir();
        Vector n = normal;

        if(q0.equals(p0)){
            return  null;
        }

        double nv = n.dotProduct(v1);
        if(isZero(nv)){
            return null;
        }

        Vector Q0P0 = q0.subtract(p0);
        double nQMinusP0 = alignZero(n.dotProduct(Q0P0));
        double t = alignZero(nQMinusP0 / nv);
        if(t <= 0)
            return null;

        return List.of(ray.getPoint(t));

    }
}
