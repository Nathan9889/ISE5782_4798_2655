package lighting;

import primitives.Color;
import primitives.Material;
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

    @Override
    public Color getIntensity(Point p){
        return super.getIntensity(p).scale(Math.max(0,_direction.dotProduct(getL(p))));


        /*Color pointIntensity = super.getIntensity(p);
        Vector l = getL(p);
        double attenuation = l.dotProduct(_direction);
        return pointIntensity.scale(Math.max(0,attenuation));*/
    }


    public SpotLight setNarrowBeam(int i){
        return this;
    }



}
