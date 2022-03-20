package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

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


    /**
     * get list of intersection of rays with triangle
     * v1 = p1 - p0, v2 = p2 - p0, v3 = p3 - p0
     * n1 = normalize(v1xv2) , n2 = normalize(v2xv3), n3 = normalize(v3xv1
     * @param ray ray that cross the triangle
     * @return list of intersection points that were found
     */
    @Override
    public List<Point> findIntersections(Ray ray){
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        var result = plane.findIntersections(ray);

        // if there is no intersections with the plane is a fortiori (kal&homer)
        // that there is no intersections with the triangle
        if(result == null){
            return null;
        }

        Vector v1 = this.vertices.get(0).subtract(p0),
                v2 = this.vertices.get(1).subtract(p0),
                v3 = this.vertices.get(2).subtract(p0);

        Vector n1 = v1.crossProduct(v2).normalize(),
                n2 = v2.crossProduct(v3).normalize(),
                n3 = v3.crossProduct(v1).normalize();

        double x1 = alignZero(v.dotProduct(n1)),
                x2 = alignZero(v.dotProduct(n2)),
                x3 = alignZero(v.dotProduct(n3));


        if(x1 < 0 && x2 < 0 && x3 < 0 || x1 > 0 && x2 > 0 && x3 > 0){
            return result;
        }

        return null;
    }


}
