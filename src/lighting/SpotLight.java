package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{

    /**
     * direction of light
     */
    private Vector _direction;


    /**
     * constructor for SpotLight
     *
     * @param intensity the light intensity
     * @param position  Light start location
     * @param direction the direction of the spot
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalize();
    }







}
