package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Interface that contains abstract functions of diffeent classes
 */
public interface Geometry {

    /**
     * get Noraml function
     * @param p1
     * @return
     */
    public Vector getNormal(Point p1);

}
