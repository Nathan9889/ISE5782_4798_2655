package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube extends Geometry {

    /**
     * Ray for the creation of tube
     */
    final Ray my_ray;

    /**
     * Radius of Tube
     */
    final double radius;


    /**
     * Constructor of Tube
     * @param my_ray
     * @param radius
     */
    public Tube(Ray my_ray, double radius) {
        if(radius < 0)
            throw new IllegalArgumentException("radius cannot be negative");
        this.my_ray = my_ray;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point p) {
        Vector pp0 = p.subtract(my_ray.getP0());
        double tube = my_ray.getDir().dotProduct(pp0);
        Point o = my_ray.getP0().add(my_ray.getDir().scale(tube));

        try{
            pp0.crossProduct(my_ray.getDir());
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("point cannot be equal to 0");
        }
        Vector v1 = p.subtract(o);
        return v1.normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
