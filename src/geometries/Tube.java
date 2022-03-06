package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry {

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
        return null;
    }
}
