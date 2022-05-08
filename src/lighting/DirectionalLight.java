package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{

   /**
    * direction of light
    */
   private Vector _direction;

   /**
    * protected ctor
    * @param intensity color intensity
    */
   public DirectionalLight(Color intensity, Vector direction) {
      super(intensity);
      this._direction = direction.normalize();
   }

   /**
    * getter of color
    * @param p point where the light touch
    * @return
    */
   @Override
   public Color getIntensity(Point p) {
      return getIntensity();
   }

   /**
    *
    * @param p The point where the light strikes
    * @return direction of light too point
    */
   @Override
   public Vector getL(Point p) {
      return _direction;
   }

   /**
    * getter distance with point
    * @param point the point to calculate the distance from
    * @return distance
    */
   @Override
   public double getDistance(Point point) {
      return Double.MAX_VALUE;
   }
}
