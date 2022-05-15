package lighting;

import primitives.*;

/**
 * interface for lighting
 */
public interface LightSource {


    /**
     * get intensity of the light according to the distance from point
     * @param p point where the light touch
     * @return color of the point
     */
    public Color getIntensity(Point p);


    /**
     * get the the direction of the light to the point where its strikes
     * @param p The point where the light strikes
     * @return the direction of the light to the point
     */
     public Vector getL(Point p);


    /**
     * get the distance between the starting point of the light source
     * @param point the point to calculate the distance from
     * @return distance between light and point
     */
    double getDistance(Point point);



}
