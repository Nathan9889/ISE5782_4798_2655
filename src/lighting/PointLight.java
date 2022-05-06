package lighting;

import primitives.Point;

public class PointLight extends Light implements LightSource{

    /**
     * The point from where the light comes out
     */
    private Point _position;
    /**
     * kC - Its purpose is to ensure that the light is not strengthened but weakened
     */
    private double _kC = 1d;
    /**
     * kL - reduce factor of attenuation of light linear dependence
     */
    private double _kL = 0d;
    /**
     * kQ - reduce factor of attenuation of light quadratic dependence
     */
    private double _kQ = 0d;

}
