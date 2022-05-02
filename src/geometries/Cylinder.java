package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {

    final double height;

    public Cylinder(Ray my_ray, double radius, double height) {
        super(my_ray, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}


