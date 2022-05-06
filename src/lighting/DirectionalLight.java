package lighting;

import primitives.Color;
import primitives.Vector;

public class DirectionalLight extends Light{

   private Vector _direction;

   /**
    * protected ctor
    *
    * @param intensity color intensity
    */
   protected DirectionalLight(Color intensity, Vector direction) {
      super(intensity);
      this._direction = direction;
   }


}
