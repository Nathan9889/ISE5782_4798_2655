package geometries;

import primitives.*;
import java.util.List;

/**
 * Interface for fiding intersections points
 */
public interface Intersectable {

    /**
     *
     * @param ray {@link Ray} point to object
     * @return List of intersections {@link Point}s
     */
    public List<Point> findIntersections(Ray ray);



}
