package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry{

    /**
     * Point to define the center of sphere
     */
    final Point p0;

    /**
     * radius of sphere
     */
    final double radius;

    /**
     * COnstructor of Sphere
     * @param p0
     * @param radius
     */
    public Sphere(Point p0, double radius) {
        if(radius < 0)
            throw new IllegalArgumentException("radius cannot be negative");
        this.p0 = p0;
        this.radius = radius;
    }

    /**
     * the normal of sphere:
     * n = normalize(p - centerPoint)
     * @param p the point on the sphere we want the normal from
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point p) {

        Vector vec = p.subtract(p0);
        return vec.normalize();

    }
}
