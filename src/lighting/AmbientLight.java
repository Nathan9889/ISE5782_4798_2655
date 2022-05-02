package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient light for basic illumination
 */
public class AmbientLight {

    private final Color intensity;

    /**
     * Ctor
     * @param Ia Color intensity
     * @param Ka Intensity factor
     */
    public AmbientLight(Color Ia , Double3 Ka){
        intensity = Ia.scale(Ka);
    }

    /**
     * Default Ctor
     */
    public AmbientLight(){
        intensity = Color.BLACK;
    }

    /**
     * getter of intensity
     * @return intensity after scaling
     */
    public Color getIntensity() {
        return intensity;
    }


}
