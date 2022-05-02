package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface that contains abstract functions of diffeent classes
 */
public abstract class Geometry extends Intersectable{

    /**
     * get Noraml function
     * @param p1
     * @return
     */
    public abstract Vector getNormal(Point p1);


    /**
     * basic emission color
     */
    protected Color emission = Color.BLACK;

    /**
     * getter for emission
     * @return emission color
     */
    public Color getEmission() {
        return emission;
    }


    /**+
     * setter for emission
     * @param emission color
     * @return geometry instance
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }



}
