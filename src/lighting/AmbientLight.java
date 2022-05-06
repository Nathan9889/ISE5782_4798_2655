package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient light for basic illumination
 */
public class AmbientLight extends Light{

    /**
     * Ctor
     * @param Ia Color intensity
     * @param Ka Intensity factor
     */
    public AmbientLight(Color Ia , Double3 Ka){
        super(Ia.scale(Ka));
    }

    /**
     * Default Ctor
     */
    public AmbientLight(){
        super(Color.BLACK);
    }


}
