package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class Sphere implements Geometry{

    /**
     * Point to define the center of sphere
     */
    final Point centre;

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
        this.centre = p0;
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

        Vector vec = p.subtract(centre);
        return vec.normalize();

    }




    /**
     * find intersections point with the sphere
     * let O be the center of the sphere, let r be the radius <br/>
     * u = O − p0 <br/>
     * tm = v * u <br/>
     * d = sqrt ( |u|^2 - tm^2 ) <br/>
     * th = sqrt ( r^2 - d^2 ) <br/>
     * if (d >= r) there are no intersections <br/>
     * t1,2 = tm +- th, p1,2 = p0 + t1,2*v <br/>
     * t1,2*v => take only if t > 0 <br/>
     * @param ray ray that cross the sphere
     * @return list of intersection points that were found
     * @throws IllegalArgumentException if the starting point of the ray equals to the center of the sphere
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        if(p0.equals(centre)){
            throw new IllegalArgumentException("p of Ray is the center of the sphere");
        }

        Vector u = centre.subtract(p0);
        double tm = u.dotProduct(v);
        double d = alignZero(Math.sqrt(u.lengthSquared() - (tm * tm) ));

        if(d >= radius){
            return null; //  no points
        }

        double th = alignZero(Math.sqrt( (radius * radius) - (d * d) ));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if(t1 > 0 && t2 > 0){
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(p1, p2);
        }

        if(t1 > 0){
            return List.of(ray.getPoint(t1));
        }
        if(t2 > 0){
            return List.of(ray.getPoint(t2));
        }
        else {
            return null; // 0 points
        }
    }
}
