package lighting;

import primitives.Color;

/**
 * abstract class light
 */
public abstract class Light {

    /**
     * intensity of ligh
     */
    final protected Color _intensity;

    /**
     * protected ctor
     * @param intensity color intensity
     */
    protected Light(Color intensity) {
        _intensity = intensity;
    }

    /**
     * getter for intensity
     * @return Color
     */
    public Color getIntensity() {
        return _intensity;
    }


}
