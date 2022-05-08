package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

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

    /**
     * constructor
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        _position = position;
    }

    /**
     * get the intensity of the light in relation to the distance from the point
     * @param p The point where the light strikes
     * @return the color of the point
     */
    @Override
    public Color getIntensity(Point p) {
        double dist = p.distance(_position);

        if(dist <= 0){
            return getIntensity();
        }

        double kCDistance = _kC;
        double kLDistance = _kL* dist;
        double kDDistance = _kQ * (dist*dist);

        double fact = (_kC + kLDistance + kDDistance);

        return getIntensity().reduce(fact);
    }


    /**
     * get the the direction of the light to the point where its strikes
     * @param p The point where the light strikes
     * @return the direction of the light to the point
     */
    @Override
    public Vector getL(Point p) {
        Vector dir = p.subtract(_position);
        return dir.normalize();
    }



    /**
     * get the distance between the starting point of the light source to some point
     * @param p the point to calculate the distance from
     * @return the distance between light and the point
     */
    @Override
    public double getDistance(Point p) {
        return _position.distance(p);
    }


    /**
     * setter for Kc
     * @param _kC
     * @return
     */
    public PointLight set_kC(double _kC) {
        this._kC = _kC;
        return this;
    }


    /**
     * setter for Kl
     * @param _kL
     * @return
     */
    public PointLight set_kL(double _kL) {
        this._kL = _kL;
        return this;
    }


    /**
     * setter for KQ
     * @param _kQ
     * @return
     */
    public PointLight set_kQ(double _kQ) {
        this._kQ = _kQ;
        return this;
    }
}
